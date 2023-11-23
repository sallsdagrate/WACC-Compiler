package wacc

import Register._

class IR {
  var mainInstructs = new MainInstructs
  var data = new Data
  var functions: List[List[Instruction]] = List()
  var templates: Map[Template, Boolean] = List(StringTemplate, IntTemplate, BoolTemplate, CharTemplate,
    OverflowTemplate, DivZeroTemplate, PrintPTemplate, PrintLnTemplate, ArrLoad,
    BoundsCheck, ArrStore, ReadiTemplate, ReadcTemplate, ArrStoreB, NullPairTemplate, FreePair).map((_, true)).toMap

  def addInstruction = mainInstructs.add _
  def addDatum = data.add _
  def addFunction(func: List[Instruction]): Unit = {
    functions = functions :+ func
  }

  def addInstructions(nis: Iterable[Instruction]): Unit = {
    nis.foreach(addInstruction)
  }


  def addData(dis: Iterable[Datum]): Unit = {
    dis.foreach(addDatum)
  }
}

class Routine(
  val name: String,
  var instructions: List[Instruction],
  val data: Data = new Data
) {
  
  def addInstruction(ni: Instruction): Unit = (instructions = instructions :+ ni)
  def addDatum = data.add _

  def addInstructions(nis: Iterable[Instruction]): Unit = {
    nis.foreach(addInstruction)
  }

  def addData(dis: Iterable[Datum]): Unit = {
    dis.foreach(addDatum)
  }
}

class MainInstructs  {
  var instructs = new Array[Instruction](0)

  def add(ni: Instruction): Unit = {
    instructs = instructs :+ ni
  }

}

class Datum(val content: String) {
  val aCount: Int = Datum.getCount
}
object Datum {
  var count: Int = -1
  def getCount: Int = {
    count = count + 1
    count
  }
}

class Data {
  var data: Array[Datum] = new Array[Datum](0)

  def add(nd: Datum): Unit = {
    data = data :+ nd
  }
}

sealed trait Template {
  def toRoutine: Routine

  def readTemplate(name: String, format: String): Routine = {
    val dm = new Datum(format)
    val routine = new Routine(name, List(
    Push(List(R0, LR)),
    Mov(R1, ImmReg(SP)),
    LoadReg(R0, dm),
    BL("scanf"),
    Pop(List(R0, PC))
    ))
    routine.addDatum(dm)
    routine
  }

  def printTemplate(name: String, format: String): Routine = {
    val dm = new Datum(format)
    val routine = new Routine(name, List(
    Push(List(LR)),
    Mov(R1, ImmReg(R0)),
    LoadReg(R0, dm),
    Printf(),
    Pop(List(PC))
    ))
    routine.addDatum(dm)
    routine
  }

  def arrayTemplate(name: String, special: Instruction): Routine = {
      val routine = new Routine(name, List(
      Push(List(LR)),
      Cmp(R10, ImmNum(0)),
      Movlt(R1, ImmReg(R10)),
      Bllt("_boundsCheck"),
      LazyLdr(LR, ImmReg(R3), -4),
      Cmp(R10, ImmReg(LR)),
      Movge(R1, ImmReg(R10)),
      Blge("_boundsCheck"),
      special,
      Pop(List(PC))
    ))
    routine
  }

  def arithmeticErrorTemplate(name: String, message: String, print: Instruction = BL("_prints")): Routine = {
    val dm = new Datum(message)
    val routine = new Routine(name, List(
    LoadReg(R0, dm),
    print,
    Mov(R0, ImmNum(255)),
    BL("exit")
    ))
    routine.addDatum(dm)
    routine
  }
}

object ArrStoreB extends Template {
  override def toRoutine: Routine = arrayTemplate("_arrStoreB", StrB(R8, R3, ImmReg(R10), false))
}



object StringTemplate extends Template {
  override def toRoutine: Routine = {
    val dm = new Datum("%.*s")
    val routine = new Routine("_prints", List(
      Push(List(LR)),
      Mov(R2,
      ImmReg(R0)),
      LazyLdr(R1, ImmReg(R0), 0),
      Printf(),
      Pop(List(PC))
      ))
    routine.addDatum(dm)
    routine
  }
}

object CharTemplate extends Template {
  override def toRoutine: Routine = printTemplate("_printc","%c")
}


object IntTemplate extends Template {
  override def toRoutine: Routine = printTemplate("_printi","%d")

}


object ReadiTemplate extends Template {
  override def toRoutine: Routine = readTemplate("_readi", "%d")
}

object ReadcTemplate extends Template {
  override def toRoutine: Routine = readTemplate("_readc", " %c")

}

object BoolTemplate extends Template {
  override def toRoutine: Routine = {
    val falseD = new Datum("false")
    val trueD = new Datum("true")
    val fstrD = new Datum("%.*s")
    val routine = new Routine("_printb", List(
      Push(List(LR)),
      Cmp(R0, ImmNum(0)),
      Bne(".L_printb0"),
      LoadReg(R2, falseD),
      Branch(".L_printb1"),
      Defun(".L_printb0"),
      LoadReg(R2, trueD),
      Defun(".L_printb1"),
      LazyLdr(R1, ImmReg(R2), -4),
      LoadReg(R0, fstrD),
      Printf(),
      Pop(List(PC))
    ))
    routine.addData(List(falseD, trueD, fstrD))
    routine
  }

}

object OverflowTemplate extends Template {
  override def toRoutine: Routine = arithmeticErrorTemplate("_errOverflow", "fatal error: integer overflow or underflow occurred\\n")

}

object DivZeroTemplate extends Template {
  override def toRoutine: Routine = arithmeticErrorTemplate("_errDivZero", "fatal error: division or modulo by zero\\n")
}

object PrintPTemplate extends Template {
  override def toRoutine: Routine = printTemplate("_printp", "%p")

}

object PrintLnTemplate extends Template {
  override def toRoutine: Routine = {
    val dm = new Datum("")
    val routine = new Routine("_println", List(
      Push(List(LR)),
      Mov(R0, ImmReg(R0)),
      LoadReg(R0, dm),
      BL("puts"),
      Mov(R0, ImmNum(0)),
      BL("fflush"),
      Pop(List(PC))
    ))
    routine.addDatum(dm)
    routine
  }

}

object NullPairTemplate extends Template {
  override def toRoutine: Routine = arithmeticErrorTemplate("_errNull", "fatal error: null pair dereferenced or freed\\n")
}

object ArrLoad extends Template {
  override def toRoutine: Routine = arrayTemplate("_arrLoad", LdrScaled(R3, ImmReg(R3), ImmReg(R10), 2))

}

object ArrLoadB extends Template {
  override def toRoutine: Routine = arrayTemplate("_arrLoadB", LdrsbReg(R3, ImmReg(R3), ImmReg(R10)))
}

object ArrStore extends Template {
  override def toRoutine: Routine = arrayTemplate("_arrStore", StrScaled(R8, ImmReg(R3), ImmReg(R10), 2))
}


object BoundsCheck extends Template {
  override def toRoutine: Routine = arithmeticErrorTemplate("_boundsCheck", "fatal error: array index %d out of bounds\\n", Printf()) 
}

object FreePair extends Template {
  override def toRoutine: Routine = {
    val routine = new Routine("_freepair", List(
      Push(List(LR)),
      Mov(R8, ImmReg(R0)),
      Cmp(R8, ImmNum(0)),
      Bleq("_errNull"),
      LazyLdr(R0, ImmReg(R8), 0),
      BL("free"),
      Mov(R0, ImmReg(R8)),
      BL("free"),
      Pop(List(PC))
    ))
    routine
  }

}