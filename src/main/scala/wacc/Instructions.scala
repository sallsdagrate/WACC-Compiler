package wacc

import Register._

/*
This file stores all the instructions that we use in the IR.
*/

trait MemLoc
// An int or a register
sealed trait IntReg
case class ImmNum(num: Int) extends IntReg
case class CharNum(num: Char) extends IntReg
case class StrNum(num: Int) extends MemLoc
case class ImmReg(reg: Register) extends IntReg with MemLoc
//Used to store a stack location relative to FP
case class StackLoc(val dec: Int) extends MemLoc {
    override def toString: String = s"[FP, #$dec]"
}

sealed trait Instruction

case class Defun(name: String) extends Instruction

case class Divmod(fst: Register, snd: Register) extends Instruction

case class Push(regs: List[Register]) extends Instruction
case class Pop(regs: List[Register]) extends Instruction

case class Mov(src: Register, dst: IntReg) extends Instruction

case class Str(src: Register, dst: Register, offset: Int, writeback: Boolean = false) extends Instruction

case class StrB(src: Register, dst: Register, offset: IntReg, writeback: Boolean = true) extends Instruction

case class Ldrr(dst:Register, adr:MemLoc) extends Instruction

case class Moveq(src: Register, dst: IntReg) extends Instruction
case class Movne(src: Register, dst: IntReg) extends Instruction
case class Movgt(src: Register, dst: IntReg) extends Instruction
case class Movge(src: Register, dst: IntReg) extends Instruction
case class Movlt(src: Register, dst: IntReg) extends Instruction
case class Movle(src: Register, dst: IntReg) extends Instruction

case class Cmp(src: Register, dst: IntReg) extends Instruction

case class Neg(reg: Register) extends Instruction

case class Branch(label: String) extends Instruction

case class Beq(label: String) extends Instruction
case class Bne(label: String) extends Instruction
case class BL(fun: String) extends Instruction
case class Blvs(fun: String) extends Instruction
case class Blne(label: String) extends Instruction
case class Bleq(label: String) extends Instruction
case class Bllt(label: String) extends Instruction
case class Blge(label: String) extends Instruction

case class Printf() extends Instruction

case class BinOpp(fst: Register, snd: Register, opp: String) extends Instruction
case class AddI(fst: Register, snd: IntReg, offset: Int) extends Instruction
case class SubI(fst: Register, snd: IntReg, offset: Int) extends Instruction

case class Adds(fst: Register, snd: Register, trd: IntReg) extends Instruction

case class Subs(fst: Register, snd: Register, trd: IntReg) extends Instruction

case class Smull(rdLo: Register, rdHi: Register, rn: Register, rm: Register) extends Instruction

case class LoadReg(dst: Register, src: Datum) extends Instruction

case class LoadRegNum(dst: Register, num: Int) extends Instruction

case class Ldr(dst: Register, src: IntReg, offset: Int = 0) extends Instruction

case class LdrScaled(dst: Register, src: IntReg, offset: IntReg, scaling: Int) extends Instruction

case class StrScaled(src: Register, dst: IntReg, offset: IntReg, scaling: Int) extends Instruction

case class LazyLdr(dst: Register, src: IntReg, offset: Int) extends Instruction

case class Ldrsb(dst: Register, src: IntReg, offset: Int) extends Instruction

case class LdrsbReg(dst: Register, src: IntReg, offset: IntReg) extends Instruction

case class ManualA(assembly: String) extends Instruction

case class Asr(rm: Register, sh: Int) extends IntReg
