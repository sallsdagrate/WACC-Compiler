package wacc

import ast._

//Register enum
object Register extends Enumeration {
    type Register = Value
    val R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, SP, LR, PC, FP = Value
}

import Register._

// Generates an internal representation of instructions given a program
object Generator {

    var ir: IR = new IR
    var branchNum = 0


    val allRegs = List(R4, R5, R6, R7, R0, R1, R2, R3, R8, R9, R10, R11, R12)

    /*
    Returns an IR given a program
    */
    def generate(tree: Program): IR = {
        ir = new IR
        branchNum = 0

        //make top memlocgenerator
        val gen = new MemLocGenerator

        //adding main label
        ir.addInstruction(Defun("main"))

        //generate the statements for the main body
        val (stats, _) = transStats(tree.stats, gen)

        //add neccessary pushes to stack
        ir.addInstruction(Push(List(FP, LR)))
        ir.addInstruction(Push(((allRegs diff List(R0, R1, R2, R3)) diff gen.regs) ++ List(R8, R10, R12)))
        ir.addInstruction(Mov(FP, ImmReg(SP)))

        ir.addInstructions(stats)
        //0 exit code by default
        ir.addInstruction(Mov(R0, ImmNum(0)))

        //add neccessary pops to stack
        ir.addInstruction(Pop(((allRegs diff List(R0, R1, R2, R3)) diff gen.regs) ++ List(R8, R10, R12)))
        ir.addInstruction(Pop(List(FP, PC)))

        //add translation of functions to IR
        tree.funcs.map((f: Func) => ir.addFunction(transFunc(f)))

        ir
    }

    /*
    takes a function and returns a list of instructions
    */
    def transFunc(func: Func): List[Instruction] = {
        val memLocGenerator = new MemLocGenerator
        val total = func.params.length * 4
        var spDiff = 0
        //all params allocated to stack by default, need to add them to memlocgenerator for function scope
        for (param <- func.params) {
            spDiff += 4
            val loc = StackLoc(total - spDiff + 8) // 8 is the offset caused by pushing FP and the LR
            memLocGenerator.memMap += (param.x.id -> loc)
            memLocGenerator.typeMap += (param.x.id -> param.x.t)
        }

        val stats = transStats(func.stats, memLocGenerator)._1
        
        List(Defun("wacc_" + func.ident.id), Push(List(FP, LR)), Mov(FP, ImmReg(SP))) ++ stats

    }

    /*
    translates a body of statements with a memloc generator. accessed from calling transStat of a scope statement
    */
    def transStats (stats: Stats, memLocGenerator: MemLocGenerator): (List[Instruction], MemLocGenerator) = {
        var instructions: List[Instruction] = List()
        stats.ss.map((s: Stat) => instructions = instructions ++ transStat(s, memLocGenerator))

        //allocates stack if needed based on translation of statements
        if(memLocGenerator.stackPos > 0) instructions = 
            List(SubI(SP, ImmReg(SP), memLocGenerator.stackPos)) ++ 
            instructions ++ 
            List(AddI(SP, ImmReg(SP), memLocGenerator.stackPos))

        (instructions, memLocGenerator)
    }

    /*
    version of transStats that does not allocate stack, as the scope needs to do this itself
    */
    def transScopeStats (stats: Stats, memLocGenerator: MemLocGenerator): (List[Instruction], MemLocGenerator) = {
        var instructions: List[Instruction] = List()
        stats.ss.map((s: Stat) => instructions = instructions ++ transStat(s, memLocGenerator))

        (instructions, memLocGenerator)
    }

    /*
    scoping used for enclosing scopes. 
    */
    def basicScoping(stats: Stats, memLocGenerator: MemLocGenerator): (List[Instruction], MemLocGenerator) = {
        val dup = memLocGenerator.duplicate
        var (body, mem) = transScopeStats(stats, dup)

        body = body ++ 
            (mem.changedKeys).reverse.map(memLocGenerator.memMap.get(_)).map(memLoc => memLoc match { 
                case None => List()
                case Some(r: ImmReg) => List(Pop(List(r.reg)))
                case Some(s: StackLoc) => List(Pop(List(R8)), Str(R8, FP, s.dec))
                case _ => throw new MatchError(memLoc)
        }).toList.flatten

        memLocGenerator.stackPos += mem.stackPos
        (body, memLocGenerator)
    }

    /*
    translates a statement into list of instructions
    */
    def transStat(stat: Stat, memLocGenerator: MemLocGenerator): List[Instruction] = {
        stat match {
            //first few are self explanatory
            case Scope(listOfStatements) => {
                basicScoping(listOfStatements, memLocGenerator)._1
            }
            case Skip() =>
                (List.empty)
            case Exit(x) =>
                (transExpr(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("exit")))
                
            case Print(x) => assemblyPrint(x, memLocGenerator)
            case Println(x) => //assemblyprint handles all printing for us
                ir.templates += (PrintLnTemplate -> true)
                assemblyPrint(x, memLocGenerator) ++ List(BL("_println"))
            case Return(x) => {
                //figure out what needs to be popped at a return
                var overshadowed: List[Instruction] = List()
                memLocGenerator.changedKeys.map(memLocGenerator.memMap.get(_) match {
                    case Some(StackLoc(s)) => overshadowed = overshadowed ++ List(Pop(List(R8)), Str(R8, FP, s))
                    case _ =>
                })
                transExpr(x, memLocGenerator) ++
                  List(Mov(R0, ImmReg(R8))) ++ overshadowed ++ List(Pop(List(FP, PC)))
            }
            case If(cond, stats1, stats2) => {
                branchNum += 1
                val b = branchNum
                //each if/else block of statements treated like a scope, pass a duplicate of memlocgenerator so 
                //it can store variables but these can be overwritten later by the original
                (transExpr(cond, memLocGenerator) ++ List(Cmp(R8, ImmNum(0)), Beq(s"elsebranch${b}")) ++
                  basicScoping(stats1, memLocGenerator.duplicate)._1 ++ List(Branch(s"end${b}"), Defun(s"elsebranch${b}")) ++
                  basicScoping(stats2, memLocGenerator.duplicate)._1 ++ List(Defun(s"end${b}")))
            }
            case While(cond, body) => {
                branchNum += 1
                val b = branchNum
                //again the block of statements used like a scope
                (List(Branch(s"cond${b}"), Defun(s"begin${b}")) ++ basicScoping(body, memLocGenerator)._1 ++
                  List(Defun(s"cond${b}")) ++ transExpr(cond, memLocGenerator) ++ List(Cmp(R8, ImmNum(1)), Beq(s"begin${b}")))
            }

            case Assign(lv, rv) => {
                //can be either typeident, ident, array elem, pair elem
                lv match {
                    case id: Ident => {
                        //if assigning into an identifier, evaluate the rv, figure out where the identifier is stored in the memMap and store it appropriately
                        val loc = memLocGenerator.memMap.getOrElse(id, null)
                        loc match {
                            case r@ImmReg(_) => {
                                //mov r8 val
                                (transRVal(rv, lv, memLocGenerator) ++ List(Mov(r.reg, ImmReg(R8))))
                            }
                            case s@StackLoc(_) => {
                                //mov r8 val
                                (transRVal(rv, lv, memLocGenerator) ++ List(Str(R8, FP, s.dec)))
                            }
                        }
                    }
                    case ae@ArrayElem(id, es) => {
                        //if allocating into an array elem, 

                        //activate the appropriate store template based on array type
                        ir.templates += (BoundsCheck -> true)
                        val ss = getArrElemType(memLocGenerator.typeMap.getOrElse(ae.id, null), es.length) match {
                            case CharT() | BoolT() => {
                                ir.templates += (ArrStoreB -> true)
                                List(BL("_arrStoreB"))
                            }
                            case _ => {
                                ir.templates += (ArrStore -> true)
                                List(BL("_arrStore"))
                            }
                        }
                        //evaluate rv, move the index into r10 and the array into r4 and store
                        transRVal(rv, memLocGenerator.duplicate) ++
                          List(Push(List(R8))) ++
                          transRVal(es.head, memLocGenerator.duplicate) ++
                          List(
                              Mov(R10, ImmReg(R8))
                          ) ++
                          transRVal(id, memLocGenerator.duplicate) ++
                          List(
                              Mov(R3, ImmReg(R8))
                          ) ++List(Pop(List(R8))) ++
                          ss


                    }
                    case elem: PairElem => {
                        //if storing into a fst or snd of pair, evaluate the rv and store at the appropriate offset
                        val (lvalue, offset) = elem match {
                            case PairElemFst(x) => (x, 0)
                            case PairElemSnd(x) => (x, 4)
                            case _ => throw new MatchError(elem)
                        }
                        pairAddr(elem, memLocGenerator) ++
                          List(Cmp(R8, ImmNum(0)), Bleq("_errNull"), Mov(R9, ImmReg(R8)),
                              Push(List(R9))) ++ transRVal(rv, lv, memLocGenerator) ++ List(Pop(List(R9)),
                            Str(R8, R9, 0))
                    }
                    case _ => {
                        List(Defun("@ could not store in type of " + lv.getClass.toString))
                    }

                }
            }

            case AssignNew(ti, rv) => {
                val (ty, id) = (ti.t, ti.id)

                // evaluate the rv
                val ss = transRVal(rv, id, memLocGenerator)

                memLocGenerator.changedKeys = memLocGenerator.changedKeys :+ ti.id
                val memLoc = memLocGenerator.memMap.get(ti.id)
                memLocGenerator.typeMap += (id -> ti.t)

                val pushInstr = memLoc match {
                    case None => {
                        // Here, assignnew is not redefining and is instead defining a new identifier not used in a previous scope
                        val loc = memLocGenerator.getNextLoc(ty)
                        memLocGenerator.memMap += (id -> loc)
                        loc match {
                            case r: ImmReg => ss ++ List(Mov(r.reg, ImmReg(R8)))
                            case s: StackLoc => {
                                ss ++ (ty match {
                                    case CharT() | BoolT() => List(StrB(R8, FP, ImmNum(s.dec), writeback = false))
                                    case _ => List(Str(R8, FP, s.dec))
                                })
                            }
                        }
                    }
                    // Here, the identifier has been used in a previous scope
                    case Some(r: ImmReg) => List(Push(List(r.reg))) ++ ss ++ List(Mov(r.reg, ImmReg(R8)))
                    case Some(s: StackLoc) => {
                        List(Ldrr(R8, s), Push(List(R8))) ++ ss ++ List(Str(R8, FP, s.dec))
                    }
                    case _ => throw new MatchError(memLoc)
                }
                pushInstr
            }

            case Read(lValue: LValue) => {
                lValue match {
                    case lValue: Ident => {
                        val dst = memLocGenerator.memMap.get(lValue)
                        memLocGenerator.typeMap.get(lValue) match {
                            case Some(IntT()) => {
                                ir.templates += (ReadiTemplate -> true)
                                // remember push and pop stuff
                                dst match {
                                    case Some(r: ImmReg) => List(BL("_readi"), Mov(r.reg, ImmReg(R0))) 
                                    case _ => throw new MatchError(dst)
                                }
                            }
                            case Some(CharT()) => {
                                ir.templates += (ReadcTemplate -> true)
                                // remember push and pop stuff
                                dst match {
                                    case Some(r: ImmReg) => List(BL("_readc"), Mov(r.reg, ImmReg(R0))) 
                                    case _ => throw new MatchError(dst)
                                }
                            }
                            case _ => throw new MatchError(lValue)
                        }
                    }
                    case pe@PairElemFst(pair: Ident) => {
                        memLocGenerator.typeMap.get(pair) match {
                            case Some(PairType(IntT(), _)) => {
                                ir.templates += (ReadiTemplate -> true)
                                pairAddr(pe, memLocGenerator) ++ List(Push(List(R8)), BL("_readi"), Pop(List(R8)), Str(R0, R8, 0))
                            }
                            case Some(PairType(CharT(), _)) => {
                                ir.templates += (ReadcTemplate -> true)
                                pairAddr(pe, memLocGenerator) ++ List(Push(List(R8)), BL("_readc"), Pop(List(R8)), Str(R0, R8, 0))
                            }
                            case _ => throw new MatchError(pair)
                        }
                    }
                    case pe@PairElemSnd(pair: Ident) => {
                        memLocGenerator.typeMap.get(pair) match {
                            case Some(PairType(_, IntT())) => {
                                ir.templates += (ReadiTemplate -> true)
                                pairAddr(pe, memLocGenerator) ++ List(Push(List(R8)), BL("_readi"), Pop(List(R8)), Str(R0, R8, 0))
                            }
                            case Some(PairType(_, CharT())) => {
                                ir.templates += (ReadcTemplate -> true)
                                pairAddr(pe, memLocGenerator) ++ List(Push(List(R8)), BL("_readc"), Pop(List(R8)), Str(R0, R8, 0))
                            }
                            case _ => throw new MatchError(pair)
                        }
                    }
                    case ArrayElem(ident, es) => {
                        //get the appropriate read instruction based on type of read and activate correct template
                        val readInstr = memLocGenerator.typeMap.get(ident) match {
                            case Some(ArrayType(IntT())) => {
                                ir.templates += (ReadiTemplate -> true)
                                List(BL("_readi"), Push(List(R0)))
                            }

                            case Some(ArrayType(CharT())) => {
                                ir.templates += (ReadcTemplate -> true)
                                List(BL("_readc"), Push(List(R0)))
                            }
                            case _ => throw new MatchError(ident)
                        }

                        //get appropriate store template
                        ir.templates += (BoundsCheck -> true)
                        val ss = getArrElemType(memLocGenerator.typeMap.getOrElse(ident, null), es.length) match {
                            case CharT() | BoolT() => {
                                ir.templates += (ArrStoreB -> true)
                                List(BL("_arrStoreB"))
                            }
                            case _ => {
                                ir.templates += (ArrStore -> true)
                                List(BL("_arrStore"))
                            }
                        }
                        
                        //return all instructions put together
                        readInstr ++
                        transRVal(ident, memLocGenerator.duplicate) ++
                        List(
                            Mov(R3, ImmReg(R8))
                        ) ++
                        transRVal(es.head, memLocGenerator.duplicate) ++
                        List(
                            Mov(R10, ImmReg(R8))
                        ) ++ List(Pop(List(R8))) ++
                        ss
                    }
                    case _ => throw new MatchError(lValue)
                }
            }

            case Free(expr: Expr) => {
                ir.templates += (FreePair-> true)
                ir.templates += (NullPairTemplate -> true)
                ir.templates += (StringTemplate -> true)
                expr match {
                    case id: Ident => {
                        val ty = memLocGenerator.typeMap.getOrElse(id, null)
                        val loc = memLocGenerator.memMap.getOrElse(id, null)
                        (loc match {
                            case r@ImmReg(_) => Mov(R0, ImmReg(r.reg))
                            case s@StackLoc(_) => LazyLdr(R0, ImmReg(FP), s.dec)
                        }) :: (ty match {
                            case PairType(_,_) => List(BL("_freepair"))
                            case _ => List(SubI(R0, ImmReg(R0), 4), BL("free"))
                        })
                    }
                    case _ => throw new MatchError(expr)
                }
            }

            case _ => throw new MatchError(stat)
        }
    }


    private def pairAddr(pairElem: PairElem, memLocGenerator: MemLocGenerator): List[Instruction] = {
        ir.templates += (NullPairTemplate -> true)
        ir.templates += (StringTemplate -> true)
        val (inner, offset) = pairElem match {
            case PairElemFst(x) => (x, 0)
            case PairElemSnd(x) => (x, 4)
        }
        inner match {
            case x : Ident => {
                val loc = memLocGenerator.memMap.getOrElse(x, null)
                val load = List(Cmp(R8, ImmNum(0)), Bleq("_errNull"),
                    LazyLdr(R8, ImmReg(R8), offset))
                loc match {
                    case r@ImmReg(_) => Mov(R8, r) :: load
                    case s@StackLoc(_) => Ldrr(R8, s) :: load
                }
            }
            case elem : PairElem => {
                pairAddr(elem, memLocGenerator) ++
                  List(LazyLdr(R8, ImmReg(R8), 0), LazyLdr(R8, ImmReg(R8), offset))
            }
            case ae : ArrayElem => {
                transExpr(ae, memLocGenerator) ++ List(LazyLdr(R8, ImmReg(R8), offset))
            }
            case _ => List(BL("missing case in pairaddr"))
        }
    }

    //given a type it will activate the correct template for represenating it
    def activateTemplate(t:Type): Unit = {
        t match {
            case BoolT() => {
                ir.templates += (BoolTemplate -> true)
            }
            case CharT() => {
                ir.templates += (CharTemplate -> true)
            }
            case StringT() => {
                ir.templates += (StringTemplate -> true)
            }
            case IntT() => {
                ir.templates += (IntTemplate -> true)
            }
            case PairType(_, _) => {
                ir.templates += (PrintPTemplate -> true)
            }
            case ArrayType(_) => {
                ir.templates += (PrintPTemplate -> true)
            }
            case _ =>
        }
    }


    //given a type and an expression print the appropriate list of instructions for printing that expression
    def printExpOfType(x:Expr, t:Type, memLocGenerator: MemLocGenerator): List[Instruction] = {
        activateTemplate(t)
        t match {
            case BoolT() => {
                transExpr(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("_printb"))
            }
            case CharT() => {
                transExpr(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("_printc"))
            }
            case StringT() => {
                transExpr(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("_prints"))
            }
            case IntT() => {
                transExpr(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("_printi"))
            }
            case PairType(_, _) => {
                transExpr(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("_printp"))
            }
            case ArrayType(t) => {
                t match {
                    //if array of chars then print as string
                    case CharT() =>
                        ir.templates += (StringTemplate -> true)
                        transRVal(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("_prints"))
                    case _ =>
                        ir.templates += (PrintPTemplate -> true)
                        transRVal(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("_printp"))
                }
            }
            case _ => throw new MatchError(t)
        }
    }

    /*
    given an expression, gives the list of instructions for printing the expression.
    most are fairly self explanatory. the general pattern is evaluate the expression, 
    store it in the correct place and link to the appropriate print statement. Sometimes 
    type needs to be identified for abiguous expressions like array elems or pair elems
    */
    def assemblyPrint(x: Expr, memLocGenerator: MemLocGenerator): (List[Instruction]) = {
        x match {
            case i@Ident(_) => {
                val t = memLocGenerator.typeMap.getOrElse(i, null)
                printExpOfType(x, t, memLocGenerator)
            }
            case ae@ArrayElem(_, _) => {
                val t = getArrElemType(memLocGenerator.typeMap.getOrElse(ae.id, null), ae.exp.length)
                printExpOfType(x, t, memLocGenerator)
            }
            case StrLiter(x) => {
                //escape chars need to be replaced so they dont mess with outputting to the .s file
                val d = new Datum(x.replace("\\", "\\\\").replace("\n", "\\n").replace("\"", "\\\"").replace("\'", "\\\'")
                .replace("\b", "\\b").replace("\f", "\\f").replace("\r", "\\r").replace("\u0000", "\\0").replace("\t", "\\t"))
                ir.addDatum(d)
                ir.templates += (StringTemplate -> true)
                (List(LoadReg(R0, d), BL("_prints")))
            }
            case x: IntExp => {
                ir.templates += (IntTemplate -> true)
                x match {
                    case IntLiter(i) => {
                        (List(Mov(R0, ImmNum(i)), BL("_printi")))
                    }
                    case _ => {
                        (transExpr(x,memLocGenerator)++List(Mov(R0,ImmReg(R8)), BL("_printi")))
                    }
                }
            }
            case x: CharExp => {
                ir.templates += (CharTemplate -> true)
                (transExpr(x, memLocGenerator) ++ List(Mov(R0, ImmReg(R8)), BL("_printc")))
            }
            case x: BoolExp => {
                ir.templates += (BoolTemplate -> true)
                x match {
                    case BoolLiter(b) => {
                        (List(Mov(R0, ImmNum(if (b) 1 else 0)), BL("_printb")))
                    }
                    case _ => {
                        (transExpr(x, memLocGenerator) ++ List(Mov(R0,ImmReg(R8)), BL("_printb")))
                    }
                }
            }
            case x: PairExp => {
                val d = new Datum("%p")
                ir.addDatum(d)
                (transExpr(x, memLocGenerator) ++ List(Mov(R1, ImmReg(R8)), LoadReg(R0, d), Printf()))
            }
            case _ => transExpr(x, memLocGenerator) ++ List(Mov(R0,ImmReg(R8)), BL("_printf"))
        }
    }

    //given an array elem type, and the number of indexes, evaluate the type of the array elem it is addressing
    def getArrElemType(aeType: Type, n: Int) : Type = {
        if (n == 0) return aeType
        aeType match {
            case ae: ArrayType => {
                getArrElemType(ae.x, n-1)
            }
            case _ => null
        }
    }

    def newline: List[Instruction] = {
        val d = new Datum("\\n")
        ir.addDatum(d)
        List(LoadReg(R0, d), Printf())
    }

    def transPairElemExpr(expr: Expr, memLocGenerator: MemLocGenerator) : List[Instruction] = {
        var mallocDec = 0
        if(expr.isInstanceOf[BoolLiter] |
          expr.isInstanceOf[BoolExp] |
          expr.isInstanceOf[CharLiter] |
          expr.isInstanceOf[CharExp]
        )
            mallocDec = 1
        else mallocDec = 4
        List(
            Mov(R0, ImmNum(mallocDec)),
            BL("malloc"),
            Mov(R12, ImmReg(R0))
        ) ++ transExpr(expr, memLocGenerator) ++ List(
            Str(R8, R12, 0),
            Mov(R8, ImmReg(R12)),
            Push(List(R8))
        )
    }

    private def pairTargetType(elem: LValue, memLocGenerator: MemLocGenerator): Type = {
        elem match {
            case x: Ident => memLocGenerator.typeMap.getOrElse(x, null)
            case PairElemFst(x) => {
                val ty = pairTargetType(x, memLocGenerator)
                ty match {
                    case PairType(t, _) => t
                    case _ => throw new MatchError(ty)
                }
            }
            case PairElemSnd(x) => {
                val ty = pairTargetType(x, memLocGenerator)
                ty match {
                    case PairType(_, t) => t
                    case _ => throw new MatchError(ty)
                }
            }
            case _ => throw new MatchError(elem)
        }
    }

    /*
    Evaluate all rvals. Translates anything that is an rval and not an expression, overwise links straight to transExpr
    All rvals should attempt to store their result in R8 for it to be moved later.
    */
    def transRVal(rv:RValue, memLocGenerator: MemLocGenerator): List[Instruction] = {
        rv match {
            case e: Expr =>
                transExpr(e, memLocGenerator)
            case c@Call(id, argList) => {
                var ss : List[Instruction] = List(Push(List(R0, R1, R2, R3, R4, R5, R6, R7)))

                var spDiff = 0
                for(a <- argList.xs) {
                    spDiff += 4
                    ss = ss ++ transExpr(a, memLocGenerator) ++
                      List(Str(R8, SP, -4, true))
                }

                ss ++ (List(BL("wacc_" + id), Mov(R8, ImmReg(R0)), Adds(SP, SP, ImmNum(spDiff)), Pop(List(R0, R1, R2, R3, R4, R5, R6, R7))))
            }
            case NewPair(e1, e2) => {
                  transPairElemExpr(e1, memLocGenerator.duplicate) ++
                  transPairElemExpr(e2, memLocGenerator.duplicate) ++
                  List(
                      Mov(R0, ImmNum(8)),
                      BL("malloc"),
                      Mov(R12, ImmReg(R0)),
                      Pop(List(R8)),
                      Str(R8, R12, 4),
                      Pop(List(R8)),
                      Str(R8, R12, 0),
                      Mov(R8, ImmReg(R12))
                  )
            }
            case elem: PairElem => List(BL("transrval called with only 2 args"))
            case ArrayLiter(xs) => {
                var factor = 0
                if (xs.nonEmpty) {
                    val arrT = xs.head
                    if (arrT.isInstanceOf[BoolLiter] |
                      arrT.isInstanceOf[BoolExp] |
                      arrT.isInstanceOf[CharLiter] |
                      arrT.isInstanceOf[CharExp]
                    )
                        factor = 1
                    else factor = 4
                }

                val arrSize = (xs.length * factor) + 4

                var ss : List[Instruction] = List()

                ss = ss ++ List(
                    Mov(R0, ImmNum(arrSize)),
                    BL("malloc"),
                    Mov(R12, ImmReg(R0)),
                    Adds(R12, R12, ImmNum(4)),
                    Mov(R8, ImmNum(xs.length)),
                    Str(R8, R12, -4)
                )

                var loc = 0
                for(x <- xs) {
                    ss = ss ++
                      transExpr(x, memLocGenerator.duplicate) ++
                      List(Str(R8, R12, loc))
                    loc += factor
                }
                ss ++ List(Mov(R8,ImmReg(R12)))

            }
            //pair elem fst, snd
            case _ => throw new MatchError(rv)
        }
    }

    /*
    overloaded version of transRval used specifically for pair translation
    */
    def transRVal(rv: RValue, lv: LValue, memLocGenerator: MemLocGenerator): List[Instruction] = {
        rv match {
            case elem: PairElem => {
                pairAddr(elem, memLocGenerator) ++ (lv match {
                    case lv: Ident => {
                        val ty = memLocGenerator.typeMap.getOrElse(lv, null)
                        ty match {
                            case CharT() => List(Ldrsb(R8, ImmReg(R8), 0))
                            case BoolT() => List(Ldrsb(R8, ImmReg(R8), 0))
                            case _ => List(LazyLdr(R8, ImmReg(R8), 0))
                        }
                    }
                    case _ => List(BL("implement lv pair assignement"))
                })
            }
            //pair elem fst, snd
            case _ => transRVal(rv, memLocGenerator)
        }
    }

    /*
    translates all exprs that are not rvals. Follows similar pattern of always returning to R8.
    Again mostly self explanatory, not many other ways to do this. similar to other code that has been done
    */
    def transExpr(expr: Expr, memLocGenerator: MemLocGenerator): List[Instruction] = {
        expr match {
            case IntLiter(x) => {
                if (x < 0 || x > 4096) {
                    List(LoadRegNum(R8, x))
                } else {
                    List(Mov(R8, ImmNum(x)))
                }
            }
            case BoolLiter(x) => List(Mov(R8, ImmNum(if (x) 1 else 0)))
            case CharLiter(x) => List(Mov(R8, ImmNum(x.toInt)))
            case StrLiter(x) => {
                val d = new Datum(x.replace("\\", "\\\\").replace("\n", "\\n").replace("\"", "\\\"").replace("\'", "\\\'")
                .replace("\b", "\\b").replace("\f", "\\f").replace("\r", "\\r").replace("\u0000", "\\0").replace("\t", "\\t"))
                ir.addDatum(d)
                List(LoadReg(R8, d))
            }
            case PairLiter() => {
                List(Mov(R8, ImmNum(0)))
            }
            case i@Ident(_) => {
                val loc = memLocGenerator.memMap.getOrElse(i, null)
                loc match {
                    case r : ImmReg => List(Mov(R8, r))
                    case s : StackLoc => {
                        memLocGenerator.typeMap.getOrElse(i, null) match {
                            case CharT() | BoolT() => List(Ldrsb(R8, ImmReg(FP), s.dec))
                            case _ => List(Ldrr(R8, loc))
                        }
                    }
                }
            }
            case ae@ArrayElem(id, exps) => {
                ir.templates += (BoundsCheck -> true)
                val t = getArrElemType(memLocGenerator.typeMap.getOrElse(ae.id, null), ae.exp.length)
                var load = BL("noload")
                ir.templates += (t match {
                    case CharT() | BoolT() =>
                        load = BL("_arrLoadB")
                        (ArrLoadB -> true)
                    case _ =>
                        load = BL("_arrLoad")
                        (ArrLoad -> true)
                })
                val loc = memLocGenerator.memMap.getOrElse(id, null)
                var ss : List[Instruction] = List()
                exps.map((e: Expr) => {
                    ss = ss ++
                    List(
                        Push(List(R3))
                    ) ++
                    transExpr(e, memLocGenerator) ++
                    List(
                        Mov(R10, ImmReg(R8)),
                        Pop(List(R3)),
                        load,
                        Mov(R8, ImmReg(R3))
                    )
                })
                loc match {
                    case r : ImmReg => List(Mov(R3, r)) ++ ss
                    case s : StackLoc => List(Ldrr(R3, loc)) ++ ss
                }
            }


            // stores the result in R8
            case Add(exp1,exp2) => {
                ir.templates += (StringTemplate -> true)
                ir.templates += (OverflowTemplate -> true)
                transExpr(exp2, memLocGenerator) ++ List(Mov(R9, ImmReg(R8)), Push(List(R9))) ++
                  transExpr(exp1, memLocGenerator) ++ List(Pop(List(R9)), Adds(R8, R8, ImmReg(R9)), Blvs("_errOverflow"))
            }

            case Sub(exp1, exp2) => {
                ir.templates += (StringTemplate -> true)
                ir.templates += (OverflowTemplate -> true)
                transExpr(exp2, memLocGenerator) ++ List(Mov(R9, ImmReg(R8)), Push(List(R9))) ++
                  transExpr(exp1, memLocGenerator) ++ List(Pop(List(R9)), Subs(R8, R8, ImmReg(R9)), Blvs("_errOverflow"))
            }

            case Mul(exp1, exp2) => {
                ir.templates += (StringTemplate -> true)
                ir.templates += (OverflowTemplate -> true)
                transExpr(exp1, memLocGenerator) ++ List(Push(List(R9)), Mov(R9, ImmReg(R8))) ++
                  transExpr(exp2, memLocGenerator) ++ List(Smull(R8, R9, R8, R9), Cmp(R9, Asr(R8, 31)),
                  Pop(List(R9)), Blne("_errOverflow"))
            }

            case Div(exp1, exp2) => {
                ir.templates += (StringTemplate -> true)
                ir.templates += (DivZeroTemplate -> true)
                divMod(exp1, exp2, ImmReg(R8), memLocGenerator) ++ List(Mov(R8, ImmReg(R0)), Pop(List(R0,R1)))
            }

            case Mod(exp1, exp2) => {
                ir.templates += (StringTemplate -> true)
                ir.templates += (DivZeroTemplate -> true)
                divMod(exp1, exp2, ImmReg(R8), memLocGenerator) ++ List(Mov(R8, ImmReg(R1)), Pop(List(R0,R1)))
            }

            case Eq(exp1, exp2) => {
                compare(exp1, exp2, memLocGenerator) ++ List(Moveq(R8, ImmNum(1)), Movne(R8, ImmNum(0)))
            }
            case NEq(exp1, exp2) => {
                compare(exp1, exp2, memLocGenerator) ++ List(Moveq(R8, ImmNum(0)), Movne(R8, ImmNum(1)))
            }
            case GT(exp1, exp2) => {
                compare(exp1, exp2, memLocGenerator) ++ List(Movgt(R8, ImmNum(1)), Movle(R8, ImmNum(0)))
            }
            case GTE(exp1, exp2) => {
                compare(exp1, exp2, memLocGenerator) ++ List(Movge(R8, ImmNum(1)), Movlt(R8, ImmNum(0)))
            }
            case LT(exp1, exp2) => {
                compare(exp1, exp2, memLocGenerator) ++ List(Movlt(R8, ImmNum(1)), Movge(R8, ImmNum(0)))
            }
            case LTE(exp1, exp2) => {
                compare(exp1, exp2, memLocGenerator) ++ List(Movle(R8, ImmNum(1)), Movgt(R8, ImmNum(0)))
            }
            
            case Or(exp1, exp2) => {
                branchNum += 1
                val b = branchNum
                transExpr(exp1, memLocGenerator) ++ List(Cmp(R8, ImmNum(0)), Bne(s"oppEnd${b}")) ++
                    transExpr(exp2, memLocGenerator) ++ List(Defun(s"oppEnd${b}"))
                // If the second expression is true then both are true and 1 is used, same with false, so no mov is necessary
            }
            case And(exp1, exp2) => {
                branchNum += 1
                val b = branchNum
                transExpr(exp1, memLocGenerator) ++ List(Cmp(R8, ImmNum(1)), Bne(s"oppEnd${b}")) ++
                    transExpr(exp2, memLocGenerator) ++ List(Defun(s"oppEnd${b}"))
                // If the second expression is true then both are true and 1 is used, same with false, so no mov is necessary
            }

            // For these, x will have the correct binary value already
            case Ord(x) => transExpr(x, memLocGenerator)
            case Chr(x) => transExpr(x, memLocGenerator)

            case Not(x) => transExpr(x, memLocGenerator) ++ List(Cmp(R8, ImmNum(0)), Moveq(R8, ImmNum(1)), Movne(R8, ImmNum(0)))
            case Negate(x) => {
                ir.templates += (StringTemplate -> true)
                ir.templates += (OverflowTemplate -> true)

                transExpr(x, memLocGenerator) ++ List(Neg(R8), Blvs("_errOverflow"))
            }
                
            case Len(x) => transExpr(x, memLocGenerator) ++ List(Ldr(R8, ImmReg(R8), -4))

            case _ => throw new MatchError(expr)
        }
    }

    // Compares the value of two expressions
    def compare(exp1: Expr, exp2: Expr, memLocGenerator: MemLocGenerator): List[Instruction] = {
        transExpr(exp2, memLocGenerator) ++ List(Mov(R9, ImmReg(R8))) ++ transExpr(exp1, memLocGenerator) ++ List(Cmp(R8, ImmReg(R9)))
    }

    // Calls divmod, the output register will depend on wether we want the div or the mod
    def divMod(exp1: Expr, exp2: Expr, out: MemLoc, memLocGenerator: MemLocGenerator): List[Instruction] = {
        var ss = List(Push(List(R0, R1)))++
          transExpr(exp1, memLocGenerator) ++ List(Mov(R0, ImmReg(R8))) ++
          transExpr(exp2, memLocGenerator) ++ List(Mov(R1, ImmReg(R8)),
            Cmp(R1, ImmNum(0)),
            Bleq("_errDivZero"),
            BL("__aeabi_idivmod"))
        out match {
            case r: ImmReg => ss = ss ++ List(Mov(R8, ImmReg(r.reg)))
            case s: StackLoc => ss = ss ++ List(Ldrr(R8, s))
        }
        return ss
    }
}


