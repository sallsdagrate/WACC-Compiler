package wacc
import parsley.Parsley
import parsley.position.pos
import parsley.implicits.zipped.Zipped2
import parsley.implicits.zipped.Zipped3
import wacc.SemanticTools.SymbolTable

object ast {

    trait ParserSingletonBridgePos[+A] {
        def con(pos: (Int, Int)): A

        def <#(op: Parsley[_]): Parsley[A] = pos.map(this.con(_)) <* op
    }

    trait ParserBridgePos0[+A] extends ParserSingletonBridgePos[A] {
        def apply()(pos: (Int, Int)): A
        def con(pos: (Int, Int)): A = this.apply()(pos)
    }

    trait ParserBridgePos1[-A, +B] extends ParserSingletonBridgePos[A => B] {
        def apply(x: A)(pos: (Int, Int)): B

        def apply(x: Parsley[A]): Parsley[B] = pos <**> x.map(this.apply(_) _)
        def con(pos: (Int, Int)): A => B = this.apply(_)(pos)
    }

    trait ParserBridgePos2[-A, -B, +C] extends ParserSingletonBridgePos[(A, B) => C] {
        def apply(x: A, y: B)(pos: (Int, Int)): C

        def apply(x: Parsley[A], y: Parsley[B]): Parsley[C] = {
            pos <**> (x, y).zipped(this.apply(_, _) _)
        }

        def con(pos: (Int, Int)): (A, B) => C = this.apply(_, _)(pos)
    }

    trait ParserBridgePos3[-A, -B, -C, +D] {
        def apply(x: A, y: B, z: C)(pos: (Int, Int)): D

        def apply(x: Parsley[A], y: Parsley[B], z: Parsley[C]): Parsley[D] =
            pos <**> (x, y, z).zipped(this.apply(_, _, _) _)

        def con(pos: (Int, Int)): (A, B, C) => D = this.apply(_, _, _)(pos)
    }

    class ast_node() {
        var sTbl: SymbolTable = null
        def addSt(st: SymbolTable): Unit = {
            sTbl = st
        }
    }

    case class Program(val funcs: List[Func], val stats: Stats)(val pos:(Int,Int)) extends ast_node {
        override def toString: String = {
            var out = ""
            for (x <- funcs) {
                out += x.toString
            }
            out + stats.toString
        }
    }

    case class Func(val ident: TypeIdent, val params: List[Param], val stats: Stats)(val pos:(Int,Int)) extends ast_node {
        override def toString: String = {
            var out = ident.toString + "("
            for (x <- params) {
                out += x.toString + ","
            }
            out + "):\n" + stats.toString + "endfunc"
        }
    }

    sealed trait Stat extends ast_node

    case class Stats(val ss: List[Stat])(val pos:(Int,Int)) extends Stat {
        override def toString: String = {
            var out : String = ""
            for (x <- ss) {
                out += x.toString + ";\n"
            }
            out
        }
    }

    case class Skip()(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "skip(" + pos.toString() + ")"
    }

    case class Read(val lvalue: LValue)(val pos:(Int,Int)) extends Stat {
        override def toString: String = "read(" + lvalue.toString + ")"
    }

    case class Println(val expr: Expr)(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "println(" + expr.toString + ")"
    }

    case class Exit(val expr: Expr)(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "exit(" + expr.toString + ")"
    }

    case class Return(val expr: Expr)(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "return(" + expr.toString + ")"
    }

    case class Free(val expr: Expr)(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "free(" + expr.toString + ")"
    }

    case class If(val cond: Expr, val ifStats: Stats, val elseStats: Stats)(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "if " + cond.toString + " then \n" +
            ifStats.toString + " else \n" + elseStats.toString + " fi"
    }

    case class While(val cond: Expr, val body: Stats)(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "while " + cond + " do \n" + body.toString + " done"
    }

    case class Print(val expr: Expr)(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "print(" + expr.toString + ")"
    }

    case class Scope(val xs: Stats)(val pos:(Int,Int)) extends Stat {
        override def toString(): String = "{" + xs.toString + "}"
    }

    sealed trait Type extends ast_node

    sealed trait PairElemType extends Type


    case class PairType(val e1: PairElemType, val e2: PairElemType)(val pos:(Int,Int)) extends Type {
        override def toString: String = "Pair(" + e1.toString +
            "," + e2.toString + ")" + "(" + pos.toString() + ")"
    }

    sealed trait Type_ extends PairElemType with Type

    case class ArrayType(val x: Type)(val pos:(Int,Int)) extends Type_ {
        override def toString: String = x.toString + "[]"
    }

    case class PairT()(val pos:(Int,Int)) extends PairElemType {
        override def toString: String = "PAIR"
    }

    sealed trait BaseType extends Type_

    case class IntT()(val pos:(Int,Int)) extends BaseType {
        override def toString: String = "INT"
    }

    case class BoolT()(val pos:(Int,Int)) extends BaseType {
        override def toString: String = "BOOL"
    }

    case class CharT()(val pos:(Int,Int)) extends BaseType {
        override def toString: String = "CHAR"
    }

    case class StringT()(val pos:(Int,Int)) extends BaseType {
        override def toString: String = "STRING"
    }

    sealed trait LValue extends ast_node

    case class TypeIdent(val t: Type, val id: Ident)(val pos:(Int,Int)) extends LValue {
        override def toString: String = t.toString + " " + id.toString
    }

    case class Param(val x: TypeIdent)(val pos:(Int,Int)) extends ast_node {
        override def toString: String = "param(" + x.toString + ")"
    }

    case class AssignNew(val x: TypeIdent, val value: RValue)(val pos:(Int,Int)) extends Stat {
        override def toString: String = x.toString + ":=" + value.toString
    }

    case class Assign(val x: LValue, val value: RValue)(val pos:(Int,Int)) extends Stat {
        override def toString: String = x.toString + ":=" + value.toString
    }

    sealed trait RValue extends ast_node

    case class ArrayLiter(val xs: List[Expr])(val pos:(Int,Int)) extends RValue {
        override def toString: String = {
            var out = "["
            for (x <- xs) {
                out += x.toString + ","
            }
            out + "]"
        }
    }

    case class ArgList(val xs: List[Expr])(val pos:(Int,Int)) extends RValue {
        override def toString: String = {
            var out = "("
            for (x <- xs) {
                out += x.toString + ","
            }
            out + ")"
        }
    }

    case class NewPair(val exp1: Expr, val exp2: Expr)(val pos:(Int,Int)) extends RValue {
        override def toString: String = "newPair(" + exp1.toString + "," +
            exp2.toString + ")"
    }

    case class Call(val ident: Ident, val args: ArgList)(val pos:(Int,Int)) extends RValue {
        override def toString: String = "call:" + ident.toString + args.toString
    }

    sealed trait PairElem extends LValue with RValue

    case class PairElemFst(val x: LValue)(val pos:(Int,Int)) extends PairElem {
        override def toString: String = "fst(" + x.toString + ")"
    }

    case class PairElemSnd(val x: LValue)(val pos:(Int,Int)) extends PairElem {
        override def toString: String = "snd(" + x.toString + ")"
    }

    sealed trait Expr extends RValue

    sealed trait IntExp  extends ast_node
    sealed trait StringExp extends ast_node
    sealed trait BoolExp extends ast_node
    sealed trait CharExp extends ast_node
    sealed trait PairExp extends ast_node
    sealed trait ArrayExp extends ast_node

    case class IntLiter(val x: Int)(val pos:(Int,Int)) extends Expr with IntExp {
        override def toString(): String = x.toString
     }
    case class BoolLiter(val x: Boolean)(val pos:(Int,Int)) extends Expr with BoolExp {
        override def toString: String = x.toString
    }
    case class CharLiter(val x: Char)(val pos:(Int,Int)) extends Expr with CharExp {
        override def toString: String = "'" + x + "'"
    }
    case class StrLiter(val x: String)(val pos:(Int,Int)) extends Expr with StringExp {
        override def toString: String = x
    }
    case class PairLiter()(val pos:(Int,Int)) extends Expr with PairExp {
        override def toString: String = "null"
    }

    sealed trait ExprOrLValue extends LValue with Expr

    case class Ident(val name: String)(val pos:(Int,Int)) extends ExprOrLValue {
        override def toString: String = name
    }

    case class ArrayElem(val id: Ident, val exp: List[Expr])(val pos:(Int,Int)) extends ExprOrLValue {
        override def toString: String = {
            var out = id.toString()
            for (x <- exp) {
                out += "[" + x.toString + "]"
            }
            out
        }
    }

    sealed trait UnOpp extends Expr

    case class Not(val exp: Expr)(val pos:(Int,Int)) extends UnOpp with BoolExp {
        override def toString(): String = "Not(" + exp.toString + ")"
    }
    case class Negate(val exp: Expr)(val pos:(Int,Int)) extends UnOpp with IntExp{
        override def toString(): String = "Neg(" + exp.toString + ")"
    }
    case class Len(val exp: Expr)(val pos:(Int,Int)) extends UnOpp with IntExp {
        override def toString(): String = "Len(" + exp.toString + ")"
    }
    case class Ord(val exp: Expr)(val pos:(Int,Int)) extends UnOpp with IntExp {
        override def toString(): String = "Ord(" + exp.toString + ")"
    }
    case class Chr(val exp: Expr)(val pos:(Int,Int)) extends UnOpp with CharExp {
        override def toString(): String = "Chr(" + exp.toString + ")"
    }



    sealed class BinOpp(val exp1: Expr, val exp2: Expr)(val pos:(Int,Int)) extends Expr

    case class Add(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with IntExp {
        override def toString(): String = "(" + exp1.toString + "+" + exp2.toString + ")"
    }
    case class Sub(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with IntExp {
        override def toString(): String = "(" + exp1.toString + "-" + exp2.toString + ")"
    }
    case class Mul(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with IntExp {
        override def toString(): String = "(" + exp1.toString + "*" + exp2.toString + ")"
    }
    case class Div(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with IntExp {
        override def toString(): String = "(" + exp1.toString + "/" + exp2.toString + ")"
    }
    case class Mod(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with IntExp {
        override def toString(): String = "(" + exp1.toString + "%" + exp2.toString + ")"
    }
    case class GT(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with BoolExp { // Greater than
        override def toString(): String = "(" + exp1.toString + ">" + exp2.toString + ")"
    }
    case class GTE(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with BoolExp {
        override def toString(): String = "(" + exp1.toString + ">=" + exp2.toString + ")"
    }
    case class LT(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with BoolExp {
        override def toString(): String = "(" + exp1.toString + "<" + exp2.toString + ")"
    }
    case class LTE(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with BoolExp {
        override def toString(): String = "(" + exp1.toString + "<=" + exp2.toString + ")"
    }
    case class Eq(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with BoolExp { // Equals
        override def toString(): String = "(" + exp1.toString + "==" + exp2.toString + ")"
    }
    case class NEq(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with BoolExp {
        override def toString(): String = "(" + exp1.toString + "!=" + exp2.toString + ")"
    }
    case class And(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with BoolExp {
        override def toString(): String = "(" + exp1.toString + "&&" + exp2.toString + ")"
    }
    case class Or(override val exp1: Expr, override val exp2: Expr)(override val pos:(Int,Int)) extends BinOpp(exp1, exp2)(pos) with BoolExp {
        override def toString(): String = "(" + exp1.toString + "||" + exp2.toString + ")"
    }

    object IdentOrArrayElem extends ParserBridgePos2[Ident, List[Expr], ExprOrLValue] {
        def apply(id: Ident, ls: List[Expr])(pos: (Int, Int)): ExprOrLValue = ls match {
            case Nil => id
            case ls => ArrayElem(id, ls)(pos)
        }
    }

    object Program extends ParserBridgePos2[List[Func], Stats, Program]

    object Scope extends ParserBridgePos1[Stats, Scope]

    object Func extends ParserBridgePos3[TypeIdent, List[Param], Stats, Func]

    object Stats extends ParserBridgePos1[List[Stat], Stats]

    object Skip extends ParserBridgePos0[Skip]

    object PairT extends ParserBridgePos0[PairT]

    object IntT extends ParserBridgePos0[IntT]

    object BoolT extends ParserBridgePos0[BoolT]

    object CharT extends ParserBridgePos0[CharT]

    object StringT extends ParserBridgePos0[StringT]

    object PairLiter extends ParserBridgePos0[PairLiter]

    object Read extends ParserBridgePos1[LValue, Read]

    object Println extends ParserBridgePos1[Expr, Println]

    object Exit extends ParserBridgePos1[Expr, Exit]

    object Return extends ParserBridgePos1[Expr, Return]

    object Free extends ParserBridgePos1[Expr, Free]

    object If extends ParserBridgePos3[Expr, Stats, Stats, If]

    object While extends ParserBridgePos2[Expr, Stats, While]

    object Print extends ParserBridgePos1[Expr, Print]

    object Param extends ParserBridgePos1[TypeIdent, Param]

    object TypeIdent extends ParserBridgePos2[Type, Ident, TypeIdent]

    object AssignNew extends ParserBridgePos2[TypeIdent, RValue, AssignNew]

    object Assign extends ParserBridgePos2[LValue, RValue, Assign]

    object PairType extends ParserBridgePos2[PairElemType, PairElemType, PairType]

    object ArrayType extends ParserBridgePos1[Type, ArrayType]

    object Call extends ParserBridgePos2[Ident, ArgList, Call]

    object NewPair extends ParserBridgePos2[Expr, Expr, NewPair]

    object ArgList extends ParserBridgePos1[List[Expr], ArgList]

    object ArrayLiter extends ParserBridgePos1[List[Expr], ArrayLiter]

    object PairElemFst extends ParserBridgePos1[LValue, PairElemFst]

    object PairElemSnd extends ParserBridgePos1[LValue, PairElemSnd]

    object ArrayElem extends ParserBridgePos2[Ident, List[Expr], ArrayElem]

    object Ident extends ParserBridgePos1[String, Ident]

    object IntLiter extends ParserBridgePos1[Int, IntLiter]

    object BoolLiter extends ParserBridgePos1[Boolean, BoolLiter]

    object CharLiter extends ParserBridgePos1[Char, CharLiter]

    object StrLiter extends ParserBridgePos1[String, StrLiter]

    object Not extends ParserBridgePos1[Expr, Not]

    object Negate extends ParserBridgePos1[Expr, Negate]

    object Len extends ParserBridgePos1[Expr, Len]

    object Ord extends ParserBridgePos1[Expr, Ord]

    object Chr extends ParserBridgePos1[Expr, Chr]

    object Add extends ParserBridgePos2[Expr, Expr, Add]

    object Sub extends ParserBridgePos2[Expr, Expr, Sub]

    object Mul extends ParserBridgePos2[Expr, Expr, Mul]

    object Div extends ParserBridgePos2[Expr, Expr, Div]

    object Mod extends ParserBridgePos2[Expr, Expr, Mod]

    object GT extends ParserBridgePos2[Expr, Expr, GT]

    object GTE extends ParserBridgePos2[Expr, Expr, GTE]

    object LT extends ParserBridgePos2[Expr, Expr, LT]

    object LTE extends ParserBridgePos2[Expr, Expr, LTE]

    object Eq extends ParserBridgePos2[Expr, Expr, Eq]

    object NEq extends ParserBridgePos2[Expr, Expr, NEq]

    object And extends ParserBridgePos2[Expr, Expr, And]

    object Or extends ParserBridgePos2[Expr, Expr, Or]
}
