package wacc
import scala.collection.mutable

object SemanticTools {
  sealed trait Obj

  class Identifier extends Obj {
    var sTbl: SymbolTable = null
    def addSt(st: SymbolTable): Unit = {
      sTbl = st
    }
  }
  class TypeObj extends Identifier {
    override def equals(obj: Any): Boolean = {
      obj match {
        case o:TypeObj => o.toString == this.toString
        case _ => false
      }
    }
  }

  case class CoercibleTypeObj() extends TypeObj {override def toString: String = "unknown"}

  case class IntegerTypeObj() extends TypeObj {override def toString: String = "int"}

  case class StringTypeObj() extends TypeObj {override def toString: String = "string"}
  case class BoolTypeObj() extends TypeObj {override def toString: String = "bool"}
  case class CharTypeObj() extends TypeObj {override def toString: String = "char"}
  class PairT() extends TypeObj
  case class PairTypeObj (val t1: TypeObj, val t2: TypeObj) extends PairT {
    override def toString: String = s"pair($t1, $t2)"
  }
  case class PairLiterObj() extends PairT {
    override def toString: String = "pairl"
  }
  case class ArrayTypeObjT (val elemTypeObj: TypeObj) extends TypeObj {
    override def toString: String = s"$elemTypeObj []"
  }
  case class ArrayTypeObj (val elemTypeObj: TypeObj, val dims:Int) extends TypeObj {
    override def toString: String = s"a$elemTypeObj []"
  }
  class FunctionObj (val returnType: TypeObj, val formals: List[TypeObj]) extends Identifier


  class SymbolTable (val encSymTbl : SymbolTable = null) {
    private val cache: mutable.Map[String, Obj] = mutable.Map()
    private val functionCache: mutable.Map[String, FunctionObj] = mutable.Map()

    def add(name: String, obj: Obj): mutable.Map[String, Obj] = {
      cache += (name -> obj)
    }

    def addFunction(name: String, fnObj: FunctionObj): mutable.Map[String, FunctionObj] = {
      functionCache += (name -> fnObj)
    }

    def lookUp(name: String): Obj = {
      cache.get(name) match {
        case None => null
        case Some(t) => t
      }
    }

    def lookUpFunction(name: String): FunctionObj = {
      functionCache.get(name) match {
        case None => null
        case Some(t) => t
      }
    }

    def lookUpAll (name:String): Obj = {
      var s = this
      while (s != null) {
        val obj = s.lookUp(name)
        if (obj != null){ return obj }
        s = s.encSymTbl
      }
      null
    }

    def lookUpFunctionAll(name: String): FunctionObj = {
      var s = this
      while (s != null) {
        val obj = s.lookUpFunction(name)
        if (obj != null & obj.isInstanceOf[FunctionObj]) {
          return obj
        }
        s = s.encSymTbl
      }
      null
    }
  }
}

