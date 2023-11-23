
package wacc

import org.scalatest.matchers.should._
import java.io.File
import parsley.io.ParseFromIO
import org.scalatest.funsuite.AnyFunSuite
import parsley.Success

class SeparateTestsSemI extends AnyFunSuite with Matchers{
  
test("wacc_examples/invalid/semanticErr/IO/readTypeErr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/IO/readTypeErr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/arrayIndexComplexNotInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/arrayIndexComplexNotInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/arrayIndexNotInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/arrayIndexNotInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/arrayMultipleIndexError.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/arrayMultipleIndexError.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/indexUndefIdent.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/indexUndefIdent.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/mixingTypesInArrays.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/mixingTypesInArrays.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/noStringIndex.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/noStringIndex.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/nonMatchingArrays.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/nonMatchingArrays.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/wrongArrayDimension.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/wrongArrayDimension.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/array/wrongArrayType.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/array/wrongArrayType.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/exit/badCharExit.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/exit/badCharExit.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/exit/exitNonInt.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/exit/exitNonInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/exit/globalReturn.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/exit/globalReturn.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/exit/returnsInMain.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/exit/returnsInMain.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/expressions/boolOpTypeErr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/expressions/boolOpTypeErr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/expressions/exprTypeErr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/expressions/exprTypeErr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/expressions/intOpTypeErr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/expressions/intOpTypeErr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/expressions/lessPairExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/expressions/lessPairExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/expressions/mixedOpTypeErr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/expressions/mixedOpTypeErr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/expressions/moreArrExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/expressions/moreArrExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/expressions/stringElemErr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/expressions/stringElemErr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/callUndefFunction.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/callUndefFunction.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/doubleArgDef.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/doubleArgDef.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/funcVarAccess.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/funcVarAccess.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionAssign.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionAssign.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionBadArgUse.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionBadArgUse.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionBadCall.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionBadCall.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionBadParam.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionBadParam.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionBadReturn.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionBadReturn.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionOverArgs.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionOverArgs.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionRedefine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionSwapArgs.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionSwapArgs.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/functionUnderArgs.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/functionUnderArgs.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/invalidReturnsBranched.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/invalidReturnsBranched.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/function/mismatchingReturns.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/function/mismatchingReturns.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/if/ifIntCondition.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/if/ifIntCondition.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/multiple/funcMess.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/multiple/funcMess.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/multiple/ifAndWhileErrs.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/multiple/ifAndWhileErrs.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/multiple/messyExpr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/multiple/messyExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/multiple/multiCaseSensitivity.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/multiple/multiCaseSensitivity.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/multiple/multiTypeErrs.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/multiple/multiTypeErrs.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/multiple/obfuscatingReturnsWithWhile.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/multiple/obfuscatingReturnsWithWhile.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/pairs/badPairAssign.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/pairs/badPairAssign.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/pairs/badPairExchange.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/pairs/badPairExchange.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/pairs/freeNonPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/pairs/freeNonPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/pairs/mismatchedPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/pairs/mismatchedPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/pairs/nonMatchingPairs.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/pairs/nonMatchingPairs.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/pairs/wrongTypeInParameterlessPair.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/pairs/wrongTypeInParameterlessPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/print/printTypeErr01.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/print/printTypeErr01.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/read/readIntoBadFst.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/read/readIntoBadFst.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/read/readIntoBadSnd.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/read/readIntoBadSnd.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/read/readTypeErr01.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/read/readTypeErr01.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/scope/badParentScope.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/scope/badParentScope.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/scope/badScopeRedefine.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/scope/badScopeRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr01.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr01.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr02.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr02.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr03.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr03.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr04.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr04.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr05.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr05.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr06.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr06.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr07.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr07.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr08.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr08.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr09.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr09.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr10.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr10.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr11.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr11.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/basicTypeErr12.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/basicTypeErr12.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/caseMatters.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/caseMatters.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/doubleDeclare.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/doubleDeclare.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/undeclaredScopeVar.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/undeclaredScopeVar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/undeclaredVar.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/undeclaredVar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/variables/undeclaredVarAccess.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/variables/undeclaredVarAccess.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/while/falsErr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/while/falsErr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/while/truErr.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/while/truErr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
test("wacc_examples/invalid/semanticErr/while/whileIntCondition.wacc") {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/invalid/semanticErr/while/whileIntCondition.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case Errors(_) =>
    case _ => assert(false)
  }
}

      
}
