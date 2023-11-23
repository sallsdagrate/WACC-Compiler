
package wacc

import org.scalatest.matchers.should._
import java.io.File
import parsley.io.ParseFromIO
import org.scalatest.funsuite.AnyFunSuite
import parsley.Success

class SeparateTestsSemantic extends AnyFunSuite with Matchers{
  
test("wacc_examples/valid/IO/IOLoop.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/IOLoop.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/IOSequence.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/IOSequence.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/hashInProgram.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/hashInProgram.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/multipleStringsAssignment.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/multipleStringsAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/print-backspace.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/print-backspace.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/print.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/print.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/printBool.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printBool.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/printChar.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/printCharArray.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printCharArray.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/printCharAsString.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printCharAsString.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/printEscChar.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printEscChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/printInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/print/println.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/println.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/read/echoBigInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoBigInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/read/echoBigNegInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoBigNegInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/read/echoChar.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/read/echoInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/read/echoNegInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoNegInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/read/echoPuncChar.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoPuncChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/IO/read/read.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/read.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/advanced/binarySortTree.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/advanced/binarySortTree.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/advanced/hashTable.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/advanced/hashTable.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/advanced/ticTacToe.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/advanced/ticTacToe.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/array.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/array.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arrayBasic.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arrayEmpty.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayEmpty.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arrayIndexMayBeArrayIndex.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayIndexMayBeArrayIndex.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arrayLength.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayLength.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arrayLookup.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayLookup.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arrayNested.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayNested.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arrayOnHeap.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayOnHeap.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arrayPrint.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayPrint.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/arraySimple.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arraySimple.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/emptyArrayAloneIsFine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayAloneIsFine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/emptyArrayNextLine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayNextLine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/emptyArrayPrint.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayPrint.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/emptyArrayReplace.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayReplace.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/emptyArrayScope.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayScope.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/free.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/free.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/lenArrayIndex.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/lenArrayIndex.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/modifyString.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/modifyString.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/array/printRef.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/printRef.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/basic/exit/exit-1.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exit-1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/basic/exit/exitBasic.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/basic/exit/exitBasic2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitBasic2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/basic/exit/exitWrap.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitWrap.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/basic/skip/comment.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/comment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/basic/skip/commentEoF.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/commentEoF.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/basic/skip/commentInLine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/commentInLine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/basic/skip/skip.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/skip.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/andExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/andExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/andOverOrExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/andOverOrExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/boolCalc.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/boolCalc.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/boolExpr1.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/boolExpr1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/charComparisonExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/charComparisonExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/divExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/divExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/equalsExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/equalsOverAnd.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverAnd.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/equalsOverBool.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverBool.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/equalsOverOr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverOr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/greaterEqExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/greaterEqExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/greaterExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/greaterExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/intCalc.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/intCalc.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/intExpr1.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/intExpr1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/lessCharExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessCharExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/lessEqExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessEqExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/lessExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/longExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/longExpr2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/longExpr3.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr3.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/longSplitExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longSplitExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/longSplitExpr2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longSplitExpr2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/minusExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/minusMinusExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusMinusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/minusNoWhitespaceExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusNoWhitespaceExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/minusPlusExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusPlusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/modExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/modExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/multExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/multExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/multNoWhitespaceExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/multNoWhitespaceExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/negBothDiv.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negBothDiv.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/negBothMod.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negBothMod.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/negDividendDiv.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDividendDiv.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/negDividendMod.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDividendMod.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/negDivisorDiv.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDivisorDiv.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/negDivisorMod.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDivisorMod.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/negExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/notExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/notExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/notequalsExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/notequalsExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/orExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/orExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/ordAndchrExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/ordAndchrExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/plusExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/plusMinusExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusMinusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/plusNoWhitespaceExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusNoWhitespaceExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/plusPlusExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusPlusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/sequentialCount.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/sequentialCount.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/expressions/stringEqualsExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/stringEqualsExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/nested_functions/fibonacciFullRec.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fibonacciFullRec.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/nested_functions/fibonacciRecursive.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fibonacciRecursive.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/nested_functions/fixedPointRealArithmetic.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fixedPointRealArithmetic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/nested_functions/functionConditionalReturn.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/functionConditionalReturn.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/nested_functions/mutualRecursion.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/mutualRecursion.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/nested_functions/printInputTriangle.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/printInputTriangle.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/nested_functions/printTriangle.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/printTriangle.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/nested_functions/simpleRecursion.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/simpleRecursion.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/argScopeCanBeShadowed.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/argScopeCanBeShadowed.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/asciiTable.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/asciiTable.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionDoubleReturn.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionDoubleReturn.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionIfReturns.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionIfReturns.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionManyArguments.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionManyArguments.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionMultiReturns.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionMultiReturns.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionReturnPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionReturnPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionSimple.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionSimple.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionSimpleLoop.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionSimpleLoop.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/functionUpdateParameter.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionUpdateParameter.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/incFunction.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/incFunction.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/manyArgumentsChar.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/manyArgumentsChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/manyArgumentsInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/manyArgumentsInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/negFunction.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/negFunction.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/punning.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/punning.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/sameArgName.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameArgName.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/sameArgName2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameArgName2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/sameNameAsVar.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameNameAsVar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/function/simple_functions/usesArgumentWhilstMakingArgument.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/usesArgumentWhilstMakingArgument.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/if1.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/if2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/if3.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if3.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/if4.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if4.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/if5.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if5.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/if6.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if6.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/ifBasic.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/ifFalse.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifFalse.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/ifTrue.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifTrue.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/if/whitespace.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/whitespace.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/checkRefPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/checkRefPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/createPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/createPair02.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair02.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/createPair03.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair03.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/createRefPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createRefPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/free.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/free.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/linkedList.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/linkedList.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/nestedPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/nestedPairLeftAssign.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPairLeftAssign.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/nestedPairRightExtract.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPairRightExtract.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/null.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/null.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/pairarray.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/pairarray.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/printNull.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printNull.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/printNullPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printNullPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/printPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/printPairOfNulls.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printPairOfNulls.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/readPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/readPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/writeFst.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/writeFst.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/pairs/writeSnd.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/writeSnd.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/divideByZero/divZero.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/divZero.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/divideByZero/divideByZero.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/divideByZero.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/divideByZero/modByZero.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/modByZero.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intJustOverflow.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intJustOverflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intUnderflow.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intUnderflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intWayOverflow.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intWayOverflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intmultOverflow.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intmultOverflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/freeNull.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/freeNull.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/readNull1.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/readNull1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/readNull2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/readNull2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/setNull1.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/setNull1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/setNull2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/setNull2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/useNull1.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/useNull1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/useNull2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/useNull2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/ifNested1.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/ifNested1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/ifNested2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/ifNested2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/indentationNotImportant.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/indentationNotImportant.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/intsAndKeywords.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/intsAndKeywords.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/printAllTypes.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/printAllTypes.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/scope.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scope.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/scopeBasic.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/scopeIfRedefine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeIfRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/scopeRedefine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/scopeSimpleRedefine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeSimpleRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/scopeVars.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeVars.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/scopeWhileNested.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeWhileNested.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/scopeWhileRedefine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeWhileRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/scope/splitScope.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/splitScope.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/sequence/basicSeq.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/basicSeq.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/sequence/basicSeq2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/basicSeq2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/sequence/boolAssignment.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/boolAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/sequence/charAssignment.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/charAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/sequence/exitSimple.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/exitSimple.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/sequence/intAssignment.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/intAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/sequence/intLeadingZeros.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/intLeadingZeros.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/sequence/stringAssignment.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/stringAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/_VarNames.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/_VarNames.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/boolDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/boolDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/boolDeclaration2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/boolDeclaration2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/capCharDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/capCharDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/charDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/charDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/charDeclaration2.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/charDeclaration2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/emptyStringDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/emptyStringDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/intDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/intDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/longVarNames.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/longVarNames.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/manyVariables.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/manyVariables.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/negIntDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/negIntDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/puncCharDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/puncCharDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/stringCarriageReturn.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/stringCarriageReturn.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/stringDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/stringDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/variables/zeroIntDeclaration.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/zeroIntDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/fibonacciFullIt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/fibonacciFullIt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/fibonacciIterative.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/fibonacciIterative.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/loopCharCondition.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/loopCharCondition.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/loopIntCondition.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/loopIntCondition.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/max.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/max.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/min.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/min.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/rmStyleAdd.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/rmStyleAdd.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/rmStyleAddIO.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/rmStyleAddIO.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/whileBasic.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/whileBoolFlip.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileBoolFlip.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/whileCount.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileCount.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/valid/while/whileFalse.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileFalse.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      
}
