package wacc

import wacc.Register.{R10, R11, R12, R4, R5, R6, R7, R8, R9, Register}
import wacc.ast.{BoolT, CharT, Ident, Type}

import scala.collection.mutable

/*
class that allows you to generate the next memory location that variables can be stored in.
Also encloses a memory map from identifier to the memloc it is stored in.
Also encloses a type map from identifier to its type
*/
class MemLocGenerator() {
    var regs: List[Register] = List(R4, R5, R6, R7,  R8, R9, R10, R11, R12)
    var stackPos: Int = 0
    var paramPos: Int = 8
    var changedKeys: Array[Ident] = Array()

    var memMap: mutable.Map[Ident, MemLoc] = mutable.Map()
    var typeMap : mutable.Map[Ident, Type] = mutable.Map()

    //get next memory location to store variable
    def getNextLoc(t:Type) : MemLoc = {
        regs match {
            case r :: rs if r < R8 =>
                regs = rs
                ImmReg(r)
            case _ =>
                t match {
                    case BoolT() | CharT() => stackPos += 1
                    case _ => stackPos += 4
                }
            StackLoc(-stackPos)
        }
    }

    //make a copy of itself to be passed into scopes
    def duplicate : MemLocGenerator = {
        val m = new MemLocGenerator
        m.regs = List()
        m.changedKeys = Array()
        m.typeMap ++= this.typeMap
        m.memMap ++= this.memMap
        m.regs = List.empty ++ this.regs
        m.stackPos = this.stackPos
        m
    }
}
