package wacc

import ast._
import SemanticTools._

object Semantic {

  private val invalidExp = (Some(new SemanticErrorBuilder("Unknown semantic error", (100, 100))), null)
  private var AST: Program = null

  /*
  builds symbol table
  program will always be AST
  encSymTbl set to null for topST and creates tree of STs
   */

  def buildSymbolTable(syntaxTree: Program, encSymTbl: SymbolTable): (Option[SemanticErrorBuilder], SymbolTable) = {
    val TopSymbolTable: SymbolTable = new SymbolTable(encSymTbl)
//    every time add the functions to the st
    for (f <- syntaxTree.funcs) {
      if(TopSymbolTable.lookUpFunction(f.ident.id.name) != null){
        val message = (s"function ${f.ident.id.name} already exists")
        return (Some(new SemanticErrorBuilder(message, f.pos)), null)
        // return (false, null)
      }
      f.addSt(TopSymbolTable)

      val newF: FunctionObj = new FunctionObj(
        mapAstTypesToSemanticTypes(f.ident.t),
        mapFnParams(f.params))
      newF.addSt(TopSymbolTable)

      TopSymbolTable.addFunction(
        f.ident.id.name,
        newF
      )


    }
    (None,TopSymbolTable)
  }

  /*
  * maps ast types to types for semantic checking
  * self explanatory
  * */
  private def mapAstTypesToSemanticTypes(ast_t: Any): TypeObj = {
//    if(ast_t.isInstanceOf[PairT]) {
//      throw new ArrayIndexOutOfBoundsException
//    }
    ast_t match {
      case _: IntT => return new IntegerTypeObj
      case _: BoolT => return new BoolTypeObj
      case _: CharT => return new CharTypeObj
      case _: StringT => return new StringTypeObj
      case at: ArrayType =>
        return new ArrayTypeObjT(mapAstTypesToSemanticTypes(at.x))
      case pt: PairType => //return new IntegerTypeObj
        var pt1 = mapAstTypesToSemanticTypes(pt.e1)
        if (pt1.isInstanceOf[PairTypeObj]) pt1 = new PairLiterObj
        var pt2 = mapAstTypesToSemanticTypes(pt.e2)
        if (pt2.isInstanceOf[PairTypeObj]) pt2 = new PairLiterObj
        return new PairTypeObj(pt1, pt2)
      case p: ast.PairT =>
        return new PairLiterObj
//      case PairT =>
//        return new PairLiterObj
      case pt : TypeObj => pt
    }
  }

/*
* maps the parameters of function to their semantic types
* */
  private def mapFnParams(allPs: Any): List[TypeObj] = {
    allPs match {
      case pl: List[Param] =>
        var pList: List[TypeObj] = List.empty[TypeObj]
        for (p <- pl) {
          pList = pList :+ mapAstTypesToSemanticTypes(p.x.t)
        }
        pList
      case _ => List.empty[TypeObj]
    } 
  }

  /*check identifier expression
  * if the identifier already exists in the st then return false
  * */
  private def CheckIdent(i: Ident, st: SymbolTable): (Option[SemanticErrorBuilder], TypeObj) = {
    val exists = st.lookUpAll(i.name)
    i.addSt(st)
    exists match {
      case null =>
        return ((Some(new SemanticErrorBuilder(s"${i.name} does not exist within scope", i.pos))), null)
      //return type of identifier
      case t: TypeObj =>
        return (None, t)
      case _ => throw new MatchError(exists)
    }
  }

  /*
  * Check the corrent number of dimensions for an array elem reference
  * recursive
  * */
  private def CheckArrDims(a: ArrayTypeObjT, i:Int): Boolean = {
    a.elemTypeObj match {
      case b:ArrayTypeObjT =>
        return CheckArrDims(b, i-1)
      case b =>
        return i <= 0
    }
  }

  /*
  * Check Array elem reference from the symbol table and using CheckArrDims*/
  private def CheckArrElem(arElem: ArrayElem, st: SymbolTable): (Option[SemanticErrorBuilder], TypeObj) = {
    arElem.addSt(st)
    val exists = st.lookUpAll(arElem.id.name)
    //check all idexes are ints

    for (e <- arElem.exp) {
      if (CheckExpr(e, st)._2 != new IntegerTypeObj) {
        return (Some(new SemanticErrorBuilder(s"invalid indexes for array elem ${arElem.id.name}", arElem.pos)), null)
      }
    }
    exists match {
      case null =>
        return (Some(new SemanticErrorBuilder(s"${arElem.id.name} does not exist within scope", arElem.pos)), null)
      case a : ArrayTypeObjT=>
        //check right amount of dimensions
        if(!CheckArrDims(a, arElem.exp.length - 1)) {
          return (Some(new SemanticErrorBuilder(s"array elems do not match $a, $arElem.exp.length", arElem.pos)), null)
        }
        return (None, a.elemTypeObj)
      case a =>
        return (Some(new SemanticErrorBuilder(s"cannot match arr elem type of type $arElem found $a", arElem.pos)), null)
    }
  }

/*
* checks binary operator expressions
* */
  private def CheckBinOp(bOp:BinOpp, st:SymbolTable): (Option[SemanticErrorBuilder], TypeObj) = {
    val be1 = CheckExpr(bOp.exp1, st)
    val be2 = CheckExpr(bOp.exp2, st)

    be1._1 match {
      case Some(error) => return (Some(error), null)
      case None =>
    }
    be2._1 match {
      case Some(error) => return (Some(error), null)
      case None =>
    }

    //if both exps not of same type then something is wrong
    def BothExpsOfType(typeObj: TypeObj): Boolean = {
      if (be1._2 != typeObj) {
        return false
      }
      else if (be2._2 != typeObj) {
        return false
      }
      else {
        return true
      }
    }

    //check correct types for all binary operations
    bOp match {
      case _:Add | _:Sub | _:Mul | _:Div | _:Mod =>
        if(!BothExpsOfType(new IntegerTypeObj)) {
          return (Some(new SemanticErrorBuilder(s"$bOp expressions are of type ${be1._2} and ${be2._2} are not of integer type", bOp.pos)), null)
        }
        return (None, new IntegerTypeObj)

      case _:GT | _:GTE | _:LT | _:LTE =>
        if (!BothExpsOfType(new IntegerTypeObj)
          & !BothExpsOfType(new CharTypeObj)) {
          return (Some(new SemanticErrorBuilder("expressions are not of integer or char type", bOp.pos)), null)
        }
        return (None, new BoolTypeObj)

      case _:Eq | _:NEq =>
        if (be1._2.isInstanceOf[ArrayTypeObj]
          || be2._2.isInstanceOf[ArrayTypeObj]){
          return (Some(new SemanticErrorBuilder("one of the expressions is an array", bOp.pos)), null)
        }
        if (!(be1._2 == be2._2)) {
          be2._2 match {
            case p:PairLiterObj => return (None, new BoolTypeObj)
            case _ =>
              return (Some(new SemanticErrorBuilder(s"expressions in ${be1._2} and ${be2._2} are not equal", bOp.pos)), null)
          }
        }
        return (None, new BoolTypeObj)

      case _:And | _:Or =>
        if (!BothExpsOfType(new BoolTypeObj)) {
          return (Some(new SemanticErrorBuilder("expressions are not of bool type", bOp.pos)), null)
        }
        return (None, new BoolTypeObj)

      case _ => throw new MatchError(bOp)
    }
  }

  /*checks unop expressions
  * same as binop*/
  private def CheckUnOp(unOpp: UnOpp, st:SymbolTable): (Option[SemanticErrorBuilder], TypeObj) = {
    //lots of pattern matching based on input and output types
    unOpp match {
      case n:Not => 
        val uEp = CheckExpr(n.exp, st)
        if(uEp._2 != new BoolTypeObj) return invalidExp
        return(None, uEp._2)
      case n:Negate =>
        val uEp = CheckExpr(n.exp, st)
        if(uEp._2 != new IntegerTypeObj) return invalidExp
        return(None, uEp._2)
      case n:Len =>
        val uEp = CheckExpr(n.exp, st)
        if(!uEp._2.isInstanceOf[ArrayTypeObjT]) return invalidExp
        return(None, new IntegerTypeObj)
      case n:Ord =>
        val uEp = CheckExpr(n.exp, st)
        if(uEp._2 != new CharTypeObj) return invalidExp
        return(None, new IntegerTypeObj)
      case n:Chr =>
        val uEp = CheckExpr(n.exp, st)
        if(uEp._2 != new IntegerTypeObj) return invalidExp
        return(None, new CharTypeObj)
      case _ =>
    }
    invalidExp
  }

  /*checks all expressions and returns (validity, type)*/
  private def CheckExpr(e: Expr, st: SymbolTable): (Option[SemanticErrorBuilder], TypeObj) = {
    e match {
      case _: IntLiter =>
        return (None, new IntegerTypeObj)
      case _: StrLiter =>
        return (None, new StringTypeObj)
      case _: BoolLiter =>
        return (None, new BoolTypeObj)
      case _: CharLiter =>
        return (None, new CharTypeObj)
      case i: Ident =>
        //see if identifier exists in scope
        return CheckIdent(i, st)
      case arElem: ArrayElem =>
        //check array identifier exists and is an array type
        return CheckArrElem(arElem, st)
        //check they are all within bounds?
      case bOp:BinOpp =>
        //Check each side has type int
        return CheckBinOp(bOp, st)
      case uOp:UnOpp =>
        //check expr is of right type for each op
        return CheckUnOp(uOp, st)
      case p:PairLiter =>
        return (None, new PairLiterObj)
      case _ =>
    }
    (Some(new SemanticErrorBuilder("Unknown semantic error", (1,1))), null)
    // invalidExp
  }

  /*Checks rvalues, returns their type*/
  private def CheckRVal(v: Any, st: SymbolTable): (TypeObj, Option[SemanticErrorBuilder]) = {
    v match {
      //check function call is valid and return returnType
      case cl: Call =>
        val exists = st.lookUpFunctionAll(cl.ident.name)
        if (exists == null) return (null, Some(new SemanticErrorBuilder("Function does not exist", cl.pos)))

        exists match {
          case f: FunctionObj =>
            if (cl.args.xs.isEmpty & f.formals.nonEmpty) {
              return (null, Some(new SemanticErrorBuilder("expected parameters in function call", cl.pos)))
            }
            else if (cl.args.xs.nonEmpty & f.formals.isEmpty) {
              return (null, Some(new SemanticErrorBuilder("function does not take any parameters", cl.pos)))
            }
            else if (cl.args.xs.isEmpty & f.formals.isEmpty) return (f.returnType, None)
            if (cl.args.xs.length != f.formals.length) return (null, Some(new SemanticErrorBuilder("Parameter count mismatch", cl.pos)))

            val clF = cl.args.xs zip f.formals
            for ((p, fp) <- clF) {
              val pType = CheckExpr(p, st)
              if (pType._2 != fp) return (null, Some(new SemanticErrorBuilder("Incorrect parameter type", cl.pos)))
            }
            return (f.returnType, None)
          case _ => return (null, Some(new SemanticErrorBuilder("Unknown error in function call", cl.pos)))
        }

      case np: NewPair =>
        //return type of new pair
        val np1 = CheckExpr(np.exp1, st)
        val np2 = CheckExpr(np.exp2, st)
        np1._1 match {
          case Some(error) =>
            error.addInfo("first expression of pair not valid")
            return (null, Some(error))
        case None =>
        }
        np2._1 match {
          case Some(error) =>
            error.addInfo("second expression of pair not valid")
            return(null, Some(error))
//          val (pt1, pt2) = ConvertPairElemTypes(np1._2, np2._2)
//          return new PairTypeObj(pt1, pt2)
          case None =>
        }
        return (new PairTypeObj(np1._2, np2._2), None)

      case arL:ArrayLiter =>
        //return elemType of array
        val T = CheckExpr(arL.xs.head, st)
        T._1 match {
          case Some(error) =>
            error.addInfo("Null array element detected")
            return (null, Some(error))
          case None =>
        }
        for (exp <- arL.xs) {
          val e = CheckExpr(exp, st)._2
          if (!e.equals(T._2)) {
            return (null, Some(new SemanticErrorBuilder(s"Expected array element type ${T._2} but found $e", arL.pos)))
          }
        }
        return (new ArrayTypeObjT(T._2), None)

      case exp: Expr =>
        //return type of exp
        val valid@(v1, v2) = CheckExpr(exp, st)
        v1 match {
          case Some(error) =>
            error.addInfo("invalid return type")
            return (null, Some(error))
          case None =>
        }
        return (v2, None)

      // pairs require extra care when handling their type
      //coercible type helps us see if their type can be forced based on abmiguity of wacc pairs
      case pe: PairElemFst =>
        val lval = CheckLVal(pe.x, st)._1
        lval match {
          case p:PairTypeObj => return (p.t1, None)
          case null => return (new PairLiterObj, None)
          case _:PairLiterObj => (new CoercibleTypeObj, None)
          case _ =>
            return (null, Some(new SemanticErrorBuilder(s"cannot take fst of type $lval", pe.pos)))
        }
      case pe: PairElemSnd =>
        val lval = CheckLVal(pe.x, st)._1
        lval match {
          case p: PairTypeObj => return (p.t2, None)
          case null => return (new PairLiterObj, None)
          case _:PairLiterObj => (new CoercibleTypeObj, None)
          case _ =>
            return (null, Some(new SemanticErrorBuilder(s"cannot take snd of type $lval", pe.pos)))
        }
      case null => return (new PairLiterObj, None)
      case _ => (null, Some(new SemanticErrorBuilder("unexpected", (1,1))))
      }

    }

  /*
  * Checks lvalue expressions, returns their type
  * similar to rval*/
  def CheckLVal(v: Any, st: SymbolTable): (TypeObj, Option[SemanticErrorBuilder]) = {
    v match {
      case cl: Ident =>
        //check if identifier in scope and return type
        val exists = st.lookUpAll(cl.name)
        if (exists == null) {
          return (null, Some(new SemanticErrorBuilder(s"${cl.name} does not exist within scope", cl.pos)))
        }
        exists match {
          case t: TypeObj => return (t, None)
          case _ => return (null, Some(new SemanticErrorBuilder("Unknown", cl.pos)))
        }

      case np: ArrayElem =>
        //return elemType of arr
        val exists@(v, t) = CheckArrElem(np, st)
        v match {
          case Some(error) =>
            error.addInfo(s"array $np does not exist")
            return (null, Some(error))
          case None =>
        }
        return (t, None)

      case pe: PairElemFst =>
        //return lval type
        val t = CheckLVal(pe.x, st)._1
        t match {
          case pt:PairTypeObj => return (pt.t1, None)
          case null => return (new PairLiterObj, None)
          case _: PairLiterObj => (new CoercibleTypeObj, None)
          case _ =>
            return (null, Some(new SemanticErrorBuilder(s"trying to take pair elem fst of $t but not a pair", pe.pos)))
        }
      case pe: PairElemSnd =>
        //return lval type
        val t = CheckLVal(pe.x, st)._1
        t match {
          case pt: PairTypeObj => return (pt.t2, None)
          case null => return (new PairLiterObj, None)
          case _: PairLiterObj => (new CoercibleTypeObj, None)
          case _ =>
            return (null, Some(new SemanticErrorBuilder(s"trying to take pair elem snd of $t but not a pair", pe.pos)))
        }
    }
  }

  /*Looks ahead to see if there are return statements before the end of the main block
  * If not in main block, retType will be nonNull so will automatically return false indicating everything is fine*/
  private def ScanForPreemptiveReturns(ss:List[Stat], retType: TypeObj): Boolean = {
    //returns true if there is a preemptive return statement in main
    //retType will be null if in main
    if(retType != null) return false
    else {
      var stCount = 1
      for (s <- ss) {
        stCount += 1
        if (s.isInstanceOf[Return] & (stCount < ss.length)) {
          return true
        }
      }
    }
    false
  }

  /*Checks lists of statements and returns if they were all valid*/
  private def CheckStats(ss: List[Stat], st: SymbolTable, retType: TypeObj): Array[SemanticErrorBuilder] = {
    var errors: Array[SemanticErrorBuilder] = Array()
    if (ScanForPreemptiveReturns(ss, retType)) {
      errors :+ (new SemanticErrorBuilder("returning from statement list too early", (2,1)))
    }
    for (s <- ss) {
      errors = errors ++ CheckStat(s, st, retType)
    }
    errors
  }

  /*Used from CheckStats to check one statement at a type and returning its validity*/
  private def CheckStat(s: Any, st: SymbolTable, retType: TypeObj): Array[SemanticErrorBuilder] = {
    s match {
      case Skip() => return Array()
      case Skip => return Array()
      case ln: Exit =>
        val e = CheckExpr(ln.expr, st)
        if ((e._1 ==None) && e._2.isInstanceOf[IntegerTypeObj]) {
          return Array()
        } else {
          return Array(new SemanticErrorBuilder("Bad exit", ln.pos))
        }

      case ln: Return =>
        if (lcdTs(CheckExpr(ln.expr, st)._2, retType)) {
          return Array()
        } else {
          return Array(new SemanticErrorBuilder("Bad return" +CheckExpr(ln.expr, st), ln.pos))
        }

      case ln: Free =>
        if (CheckFreeStat(st, ln)) {
          return Array()
        } else {
          return Array(new SemanticErrorBuilder("Bad Free", ln.pos))
        }

      case c: If =>
        return CheckIfStat(st, c, retType)

      case w: While =>
        return CheckWhileStat(st, w, retType)

      case b: Scope =>
        return CheckBeginStat(st, b, retType)

      case ass: Assign =>
        CheckAssignStat(st, ass) match {
          case None => return Array()
          case Some(error) => return Array(error)
        }

      case ass: AssignNew =>
        return CheckAssignNewStat(st, ass) match {
          case None => return Array()
          case Some(error) => return Array(error)
        }

      case Print(e) =>
        return CheckExpr(e, st)._1 match {
          case None => return Array()
          case Some(error) => return Array(error)
        }

      case Println(e) =>
        return CheckExpr(e, st)._1 match {
          case None => return Array()
          case Some(error) => return Array(error)
        }

      case r: Read =>
        if (CheckReadStat(st, r)) {
          return Array()
        } else {
          return Array(new SemanticErrorBuilder("Bad Read", r.pos))
        }
    }
  }



  /*Helper functions for assignments and return type checking
  * ---------------------------------*/
  /*Checks if a variable is being assigned an empty array in which case CheckRValue will fail so this is necessary*/
  private def CheckForEmptyArrAssignment(lv:TypeObj, rv: Any): Boolean = {
    lv match {
      case _: ArrayTypeObjT =>
        rv match {
          case ar: ArrayLiter =>
            return ar.xs.isEmpty
          case _ => return false
        }
      case _ => return false
    }
  }
  /*Handles edge cases for pair matches which coercibility does not handle*/
  private def CheckPairMatch(t1: TypeObj, t2: TypeObj): Boolean = {
    (t1, t2) match {
      case (PairTypeObj(_, _), PairLiterObj()) => return true
      case (PairTypeObj(PairLiterObj(), PairLiterObj()), PairTypeObj(PairTypeObj(_, _), PairTypeObj(_, _))) => return true
      case (PairTypeObj(_, _), null) => return true
      case (PairLiterObj(), null) => return true
      case (PairLiterObj(), PairTypeObj(_, _)) => return true
      case (PairTypeObj(PairLiterObj(), a), PairTypeObj(PairTypeObj(_, _), b)) =>
        return a == b
      case (PairTypeObj(a, PairLiterObj()), PairTypeObj(b, PairTypeObj(_, _))) =>
        return a == b
      case (a:ArrayTypeObjT, b:ArrayTypeObjT) =>
        return CheckPairMatch(a.elemTypeObj, b.elemTypeObj)
      case _ =>
    }
    t1 == t2
  }
  /*Calculates lowest common denominator of two types to see if they match, specifically involving pairs as they are annoying*/
  private def lcdTs(t1:TypeObj, t2:TypeObj): Boolean = {
    if (t1 == t2) return true
    (t1, t2) match {
      case (PairTypeObj(a, b), PairTypeObj(x, y)) =>
        return lcdTs(a, x) & lcdTs(b, y)
      case (a@PairTypeObj(_, _), PairLiterObj()) =>
        return true
      case (PairLiterObj(), a@PairTypeObj(_, _)) =>
        return true
      case _ => false
    }
  }
  /*---------------------------------*/

  /*Assignment statements*/
  private def CheckAssignStat(st: SymbolTable, ass: Assign): Option[SemanticErrorBuilder] = {
    ass.x match {
      case as: Ident =>
        //check lval and rval are of same type
        val lval = CheckLVal(as, st)._1
        //check for empty brackets
        if (CheckForEmptyArrAssignment(lval, ass.value)) return None
        val assT = CheckRVal(ass.value, st)._1
        if (CheckPairMatch(lval, assT) || lcdTs(lval, assT)) return None
        return if(lval == assT) {
          None
        } else {
          new Some(new SemanticErrorBuilder("bad pair", as.pos))
        }

      case arElem: ArrayElem =>
        //if lval is an array elem then needs to be handled a little differently but basically the same
        val check = CheckLVal(arElem.id, st)._1
        var elemT: TypeObj = null
        check match {
          case a:ArrayTypeObjT =>
            elemT = a.elemTypeObj
          case _ =>
            return Some(new SemanticErrorBuilder(s"Could not find array type of name ${arElem.id}", arElem.pos))
        }
        //check for empty brackets
        if(CheckForEmptyArrAssignment(elemT, ass.value)) return None
        val rval = CheckRVal(ass.value, st)._1
        if (CheckPairMatch(elemT, rval) || lcdTs(elemT, rval)) return None
        check match {
          case arT: ArrayTypeObjT =>
            if (arT.elemTypeObj.equals(CheckRVal(ass.value, st)._1)) {
              return None
            } else {
              return Some(new SemanticErrorBuilder(s"Semantic error", arElem.pos))
            }
          case _ =>
            return Some(new SemanticErrorBuilder(s"Could not resolve type of array elem $arElem", arElem.pos))
        }
      case _: PairElemFst | _: PairElemSnd =>
        //pairs also need extra care, coercibility involved
        val lv = CheckLVal(ass.x, st)._1
        val rv = CheckRVal(ass.value, st)._1
        (lv, rv) match {
          case (CoercibleTypeObj(), CoercibleTypeObj()) =>
            return Some(new SemanticErrorBuilder("Cannot assign unknown to unknown type", ass.pos))
          case (CoercibleTypeObj(), _:TypeObj) =>
            return None
          case (_:TypeObj, CoercibleTypeObj()) =>
            return None
          case _ =>
            val r = lv == rv || CheckPairMatch(lv, rv)
            if(!r){
              return Some(new SemanticErrorBuilder(
                s"${ass.x} of type ${CheckLVal(ass.x, st)._1} and ${ass.value} of type ${CheckRVal(ass.value, st)._1} do not match", ass.pos
                ))
            }
            return None
        }
      
      case _ => throw new MatchError(ass.x)
    }
  }

  /*New Assignment statements for freshly defined variables*/
  private def CheckAssignNewStat(st: SymbolTable, defin: AssignNew): Option[SemanticErrorBuilder] = {
    //THIS IS A VERY COMPLICATED FUNCTION

    //check the new variable doesnt already exist
    val exists = st.lookUp(defin.x.id.name)
    if (exists != null & !exists.isInstanceOf[FunctionObj]) {
      return Some(new SemanticErrorBuilder(
        s"${defin.x.id.name} already exists in this scope as type $exists. Cannot redefine to ${defin.value}", defin.pos))
    }

    //map it to a semantic type
    val newT = mapAstTypesToSemanticTypes(defin.x.t)

    //make sure it doesnt fail all of the helper functions
    if (!CheckForEmptyArrAssignment(newT, defin.value)) {
      val assT = CheckRVal(defin.value, st)._1
      if(!assT.isInstanceOf[CoercibleTypeObj] & !CheckPairMatch(newT, assT)){
        if(newT != assT) {
      return Some(new SemanticErrorBuilder((s"type $defin of type ${mapAstTypesToSemanticTypes(defin.x.t)} " +
            s"not compatable with type of ${defin.value} of type ${assT}"), defin.pos))
        }
      }
    }

    defin.addSt(st)

    newT.addSt(st)
    //as long as everything is fine and dandy then add to the symbol table
    st.add(
      defin.x.id.name,
      newT
    )
    return None
  }

  /*Various functions for handling types of statements
  * All of them revolve around what types of expressions each statement can take and rejects otherwise
  *
  * Statements like while, begin and if involve scope
  * for this we make new child table from the current one being passed around.
  * This is used until the scope is exited
  * ----------------------------------------- */
  private def CheckReadStat(st: SemanticTools.SymbolTable, r: Read): Boolean = {
    val lval = CheckLVal(r.lvalue, st)._1
    return lval.isInstanceOf[CharTypeObj] || lval.isInstanceOf[IntegerTypeObj]
  }

  private def CheckBeginStat(st: SymbolTable, b: Scope, retType: TypeObj): Array[SemanticErrorBuilder] = {
    if (ScanForPreemptiveReturns(b.xs.ss, retType)) return (Array())
    //           continue with new scope
    val childST = buildSymbolTable(AST, st)._2
    return CheckStats(b.xs.ss, childST, retType)
  }

  private def CheckFreeStat(st: SymbolTable, ln: Free): Boolean = {
    val e = CheckExpr(ln.expr, st)
    return ((e._1 == None) &
          (e._2.isInstanceOf[PairTypeObj]
          || e._2.isInstanceOf[ArrayTypeObj]
              || e._2.isInstanceOf[ArrayTypeObjT]))
  }

  private def CheckWhileStat(st: SymbolTable, w: While, retType: TypeObj): Array[SemanticErrorBuilder] = {
    val condCheck = CheckExpr(w.cond, st)
    //        only if cond is a valid boolean expression keep checking
    condCheck match {
      case (Some(error), _) =>
        error.addInfo("condition is not valid")
        return Array(error)
      case (None, c2: BoolTypeObj) =>
        if (ScanForPreemptiveReturns(w.body.ss, retType)) return Array(new SemanticErrorBuilder("Returned too soon", w.pos))
        //           continue with new scope
        val childST = buildSymbolTable(AST, st)._2
        val r = CheckStats(w.body.ss, childST, retType)
        return r
      case (None, t) =>
        return Array(new SemanticErrorBuilder(s"condition is of type $t, not of type boolean", w.pos))
    }
  }

  private def CheckIfStat(st: SymbolTable, c: If, retType: TypeObj): Array[SemanticErrorBuilder] = {
    val condCheck = CheckExpr(c.cond, st)
    condCheck._1 match {
      case Some(error) => return Array(error)
      case None =>
    }
    if (condCheck._2 != new BoolTypeObj) {
      return Array(new SemanticErrorBuilder(s"condition is of type ${condCheck._2} not of type boolean", c.pos))
    } else {
      var childSt = buildSymbolTable(AST, st)._2
      if (ScanForPreemptiveReturns(c.ifStats.ss, retType)) return Array(new SemanticErrorBuilder("Returned too soon", c.pos))
      val ifS = CheckStats(c.ifStats.ss, childSt, retType)
      childSt = buildSymbolTable(AST, st)._2
      if (ScanForPreemptiveReturns(c.elseStats.ss, retType)) return Array(new SemanticErrorBuilder("Returned too soon", c.pos))
      val elS = CheckStats(c.elseStats.ss, childSt, retType)
      return (ifS ++ elS)
    }
  }
  /* ----------------------------------------- */

  /*function to add a parameter to the symbol table*/
  private def addParamToTable(st:SymbolTable, p:Param): Option[SemanticErrorBuilder] = {
    if(st.lookUp(p.x.id.name) != null){
      return Some(new SemanticErrorBuilder(s"$p already exists as a parameter", p.pos))
    }
    val newT: TypeObj = mapAstTypesToSemanticTypes(p.x.t)
    p.addSt(st)
    newT.addSt(st)
    st.add(p.x.id.name, newT)
    None
  }

  /*function analysis will perform semantic analysis on a function and return its validity*/
  private def functionAnalysis(func: Func, st: SymbolTable): Array[SemanticErrorBuilder] ={
    val paramSt = buildSymbolTable(AST, st)._2
    //param table used to allow new variables in the function to overshadow the parameters
    val childSt = buildSymbolTable(AST, paramSt)._2
    //ass function parameters as variables in function symbol table
      for (p <- func.params) {
        addParamToTable(paramSt, p) match {
          case Some(error) => return Array(error)
          case None =>
        }
      } 
    //check the statements of each function using the child st
    CheckStats(func.stats.ss, childSt, mapAstTypesToSemanticTypes(func.ident.t))
  }

  //top function to perform semantic analysis on the whole program
  def semanticAnalysis(syntaxTree: Program): SemResult = {
    var errors: Array[SemanticErrorBuilder] = Array()
    AST = syntaxTree

//    build symbol table
    val (stValid, st) = buildSymbolTable(AST, null)
    stValid match {
      case Some(error) =>
        error.addInfo("could not build symbol table")
        return Errors(Array(error))
      case None =>
//        do function analysis
        for (f <- syntaxTree.funcs) {
          errors = errors ++ functionAnalysis(f, st)
        }
      }
//    check statements
    errors = errors ++ CheckStats(syntaxTree.stats.ss, st, null)
    if (errors.isEmpty) {
      AST.addSt(st)
      ProgramAndTable(AST, st)
    } else {
      Errors(errors)
    }
  }

}
