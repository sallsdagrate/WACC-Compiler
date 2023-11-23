package wacc

import Register._

/*
handles all writing of assembly. Can directly translate an IR into assembly code.
*/
object AssemblyWriter {

    // converts an IR to assembly
    def irToAssembly(ir: IR): String = {

        val dataAsm = dataToAssembly(ir.data)
        val mainAsm = mainInstructToAssembly(ir.mainInstructs)
        val functionAsm = ir.functions.map{f => f.map(instructionToAssembly).mkString("\n")}.mkString("\n")

        // Generate assembly only if the template is used
        val templateAsm = ir.templates.filter(_._2).keys.map(templateToAssembly(_)).mkString("\n") 
        s"$dataAsm\n.text\n.global main\n\n$mainAsm\n\n$functionAsm\n$templateAsm\n"
    }

    // converts data to assembly
    def dataToAssembly(data: Data): String = {

        val dataAsm = data.data.map{d =>
            s"""@ length of L.str${d.aCount}
                |\t.word ${d.content.length}
                |.L.str${d.aCount}:
                |\t.asciz "${d.content}"
                |""".stripMargin
        }.mkString("\n")

        // if there's no data, nothing is generated
        if (dataAsm.isEmpty) {""} else {s".data\n${dataAsm}"}
    }
    
    // converts main instructions to assembly
    def  mainInstructToAssembly(mainInstructs: MainInstructs): String = {
        mainInstructs.instructs.map(instructionToAssembly(_)).mkString("\n")
    }

    //match all possibly instrs to their assembly representation
    def instructionToAssembly(instruction: Instruction): String = {
        instruction match {
            case Defun(name: String) => s"${name}:"
            case Push(regs: List[Register]) => s"\tpush {${regs.mkString(", ")}}"
            case Pop(regs: List[Register]) => s"\tpop {${regs.mkString(", ")}}"
            case Mov(src: Register, dst: IntReg) => s"\tmov ${src}, ${intRegToAssembly(dst)}"
            case Str(src: Register, dst: Register, offset: Int, writeback: Boolean) => s"\tstr $src, [$dst, #$offset]" + (if (writeback) "!" else "")
            case StrB(src: Register, dst: Register, offset: IntReg, writeback: Boolean) => s"\tstrb $src, [$dst, ${intRegToAssembly(offset)}]" + (if (writeback) "!" else "")
            case Ldrr(dst: Register, adr: MemLoc) => s"\tldr $dst, ${memLocToAssembly(adr)}"
            case Moveq(src: Register, dst: IntReg) => s"\tmoveq ${src}, ${intRegToAssembly(dst)}"
            case Movne(src: Register, dst: IntReg) => s"\tmovne ${src}, ${intRegToAssembly(dst)}"
            case Movgt(src: Register, dst: IntReg) => s"\tmovgt ${src}, ${intRegToAssembly(dst)}"
            case Movge(src: Register, dst: IntReg) => s"\tmovge ${src}, ${intRegToAssembly(dst)}"
            case Movlt(src: Register, dst: IntReg) => s"\tmovlt ${src}, ${intRegToAssembly(dst)}"
            case Movle(src: Register, dst: IntReg) => s"\tmovle ${src}, ${intRegToAssembly(dst)}"
            case Cmp(src: Register, dst: IntReg) => s"\tcmp ${src}, ${intRegToAssembly(dst)}"
            case Neg(reg: Register) => s"\trsbs ${reg}, ${reg}, #0"
            case Branch(label: String) => s"\tb ${label}"
            case Beq(label: String) => s"\tbeq ${label}"
            case Bne(label: String) => s"\tbne ${label}"
            case BL(fun: String) => s"\tbl ${fun}"
            case Blvs(fun: String) => s"\tblvs ${fun}"
            case Blne(label: String) => s"\tblne ${label}"
            case Bleq(label: String) => s"\tbleq ${label}"
            case Bllt(label: String) => s"\tbllt ${label}"
            case Blge(label: String) => s"\tblge ${label}"
            case Printf() => instructionToAssembly(BL("printf")) + "\n" + instructionToAssembly(Mov(R0, ImmNum(0))) + "\n" + instructionToAssembly(BL("fflush"))
            case BinOpp(fst: Register, snd: Register, opp: String) => s"\t${opp} ${fst}, ${snd}"
            case AddI(fst: Register, snd: IntReg, offset: Int) => s"\tadd ${fst}, ${intRegToAssembly(snd)}, #$offset"
            case SubI(fst: Register, snd: IntReg, offset: Int) => s"\tsub ${fst}, ${intRegToAssembly(snd)}, #$offset"
            case Adds(fst: Register, snd: Register, trd: IntReg) => s"\tadds ${fst}, ${snd}, ${intRegToAssembly(trd)}"
            case Subs(fst: Register, snd: Register, trd: IntReg) => s"\tsubs ${fst}, ${snd}, ${intRegToAssembly(trd)}"
            case Smull(rdLo: Register, rdHi: Register, rn: Register, rm: Register) => s"\tsmull ${rdLo}, ${rdHi}, ${rn}, ${rm} "
            case LoadReg(dst: Register, src: Datum) => s"\tldr ${dst}, =.L.str${src.aCount}"
            case LoadRegNum(dst: Register, num: Int) => s"\tldr ${dst}, =${num}"
            case Ldr(dst: Register, src: IntReg, offset: Int) => {
                if (offset == 0) {
                    s"\tldr ${dst}, ${intRegToAssembly(src)}"
                }
                else {
                    s"\tldr ${dst}, [${intRegToAssembly(src)}, #${offset}]"
                }
            }
            case LazyLdr(dst: Register, src: IntReg, offset: Int) => s"\tldr ${dst}, [${intRegToAssembly(src)}, #${offset}]"
            case LdrScaled(dst: Register, src: IntReg, offset: IntReg, scaling: Int) => s"ldr $dst, [${intRegToAssembly(src)}, ${intRegToAssembly(offset)}, LSL #$scaling]"
            case StrScaled(src: Register, dst: IntReg, offset: IntReg, scaling: Int) => s"str $src, [${intRegToAssembly(dst)}, ${intRegToAssembly(offset)}, LSL #$scaling]"
            case Ldrsb(dst: Register, src: IntReg, offset: Int) => s"\tldrsb ${dst}, [${intRegToAssembly(src)}, #${offset}]"
            case LdrsbReg(dst: Register, src: IntReg, offset: IntReg) => s"\tldrsb ${dst}, [${intRegToAssembly(src)}, ${intRegToAssembly(offset)}]"
            case Divmod(fst: Register, snd: Register) => instructionToAssembly(Mov(R0, ImmReg(fst))) + "\n" +  instructionToAssembly(Mov(R1, ImmReg(snd))) + "\n" + "\tbl __aeabi_idivmod"
            case ManualA(assembly: String) => assembly
        }
    }

    //matches an intreg to its assembly
    def intRegToAssembly(intReg: IntReg): String = {
        intReg match {
            case ImmNum(num) => s"#${num}"
            case CharNum(num) => s"#${num.toInt}"
            case ImmReg(reg) => reg.toString()
            case Asr(rm: Register, sh: Int) => s"${rm}, asr #${sh}"
        }
    }

    // converts a routine to its assembly
    def routineToAssembly(routine: Routine): String = {
        val dataAsm = dataToAssembly(routine.data)
        val instrAsm = routine.instructions.map(instructionToAssembly(_)).mkString("\n")
        s"${dataAsm}\n.text\n${routine.name}:\n${instrAsm}\n"
    }

    //match types of memory locations to their assembly
    def memLocToAssembly(memLoc: MemLoc): String = {
        memLoc match {
            case StrNum(num) => s"=.L.str$num"
            case ImmReg(reg) => reg.toString()
            case StackLoc(dec) => s"[FP, #$dec]"
        }
    }

    //translates a template/routine into assembly
    def templateToAssembly(template: Template): String = {
        routineToAssembly(template.toRoutine)
    }

}

