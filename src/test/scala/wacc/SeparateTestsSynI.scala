
package wacc

import org.scalatest.matchers.should._
import java.io.File
import parsley.io.ParseFromIO
import org.scalatest.funsuite.AnyFunSuite

class SeparateTestsSynI extends AnyFunSuite with Matchers{
  
test("wacc_examples/invalid/syntaxErr/array/arrayExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/array/arrayExpr.wacc")).get
  assert(!result.isSuccess, raw"array literals are not first-class expressions")
}

      
test("wacc_examples/invalid/syntaxErr/basic/badComment.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/badComment.wacc")).get
  assert(!result.isSuccess, raw"program has missing comment declaration")
}

      
test("wacc_examples/invalid/syntaxErr/basic/badComment2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/badComment2.wacc")).get
  assert(!result.isSuccess, raw"program has missing in-line comment declaration")
}

      
test("wacc_examples/invalid/syntaxErr/basic/badEscape.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/badEscape.wacc")).get
  assert(!result.isSuccess, raw"program has unacceptable escape character")
}

      
test("wacc_examples/invalid/syntaxErr/basic/beginNoend.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/beginNoend.wacc")).get
  assert(!result.isSuccess, raw"begin missing closing end")
}

      
test("wacc_examples/invalid/syntaxErr/basic/bgnErr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/bgnErr.wacc")).get
  assert(!result.isSuccess, raw"begin token typo")
}

      
test("wacc_examples/invalid/syntaxErr/basic/multipleBegins.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/multipleBegins.wacc")).get
  assert(!result.isSuccess, raw"begin missing closing end")
}

      
test("wacc_examples/invalid/syntaxErr/basic/noBody.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/noBody.wacc")).get
  assert(!result.isSuccess, raw"program missing body")
}

      
test("wacc_examples/invalid/syntaxErr/basic/skpErr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/skpErr.wacc")).get
  assert(!result.isSuccess, raw"skip typo")
}

      
test("wacc_examples/invalid/syntaxErr/basic/unescapedChar.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/basic/unescapedChar.wacc")).get
  assert(!result.isSuccess, raw"unescaped double quote")
}

      
test("wacc_examples/invalid/syntaxErr/expressions/missingOperand1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/expressions/missingOperand1.wacc")).get
  assert(!result.isSuccess, raw"operator missing first operand")
}

      
test("wacc_examples/invalid/syntaxErr/expressions/missingOperand2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/expressions/missingOperand2.wacc")).get
  assert(!result.isSuccess, raw"operator missing second operand")
}

      
test("wacc_examples/invalid/syntaxErr/expressions/printlnConcat.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/expressions/printlnConcat.wacc")).get
  assert(!result.isSuccess, raw"string concatenation is not part of the language")
}

      
test("wacc_examples/invalid/syntaxErr/function/badlyNamed.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/badlyNamed.wacc")).get
  assert(!result.isSuccess, raw"function return type and name are conflated")
}

      
test("wacc_examples/invalid/syntaxErr/function/badlyPlaced.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/badlyPlaced.wacc")).get
  assert(!result.isSuccess, raw"program has function before begin")
}

      
test("wacc_examples/invalid/syntaxErr/function/funcExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/funcExpr.wacc")).get
  assert(!result.isSuccess, raw"function calls are not first-class expressions")
}

      
test("wacc_examples/invalid/syntaxErr/function/funcExpr2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/funcExpr2.wacc")).get
  assert(!result.isSuccess, raw"trying to call a function in a general expression")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionConditionalNoReturn.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionConditionalNoReturn.wacc")).get
  assert(!result.isSuccess, raw"function body missing return or exit statement on a path")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionEndingNotReturn.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionEndingNotReturn.wacc")).get
  assert(!result.isSuccess, raw"function body not terminated with return or exit statement")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionLateDefine.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionLateDefine.wacc")).get
  assert(!result.isSuccess, raw"attempted function definition after body begun")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionMissingCall.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionMissingCall.wacc")).get
  assert(!result.isSuccess, raw"program missing call token at function use")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionMissingPType.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionMissingPType.wacc")).get
  assert(!result.isSuccess, raw"program missing parameter type in function")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionMissingParam.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionMissingParam.wacc")).get
  assert(!result.isSuccess, raw"program extra missing parameter in function definition")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionMissingType.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionMissingType.wacc")).get
  assert(!result.isSuccess, raw"program missing return type of function")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionNoReturn.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionNoReturn.wacc")).get
  assert(!result.isSuccess, raw"function body missing return or exit statement")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionReturnInLoop.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionReturnInLoop.wacc")).get
  assert(!result.isSuccess, raw"function body missing a guaranteed return (the loop might not be entered)")
}

      
test("wacc_examples/invalid/syntaxErr/function/functionScopeDef.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/functionScopeDef.wacc")).get
  assert(!result.isSuccess, raw"attempted function definition in new scope")
}

      
test("wacc_examples/invalid/syntaxErr/function/mutualRecursionNoReturn.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/mutualRecursionNoReturn.wacc")).get
  assert(!result.isSuccess, raw"function body of r2 missing return")
}

      
test("wacc_examples/invalid/syntaxErr/function/noBodyAfterFuncs.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/noBodyAfterFuncs.wacc")).get
  assert(!result.isSuccess, raw"program missing body after function declaration")
}

      
test("wacc_examples/invalid/syntaxErr/function/thisIsNotC.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/function/thisIsNotC.wacc")).get
  assert(!result.isSuccess, raw"Uses C-style pointers. This is WACC, not C.")
}

      
test("wacc_examples/invalid/syntaxErr/if/ifNoelse.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/if/ifNoelse.wacc")).get
  assert(!result.isSuccess, raw"if missing else clause")
}

      
test("wacc_examples/invalid/syntaxErr/if/ifNofi.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/if/ifNofi.wacc")).get
  assert(!result.isSuccess, raw"if missing closing fi")
}

      
test("wacc_examples/invalid/syntaxErr/if/ifNothen.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/if/ifNothen.wacc")).get
  assert(!result.isSuccess, raw"if missing then clause")
}

      
test("wacc_examples/invalid/syntaxErr/if/ifiErr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/if/ifiErr.wacc")).get
  assert(!result.isSuccess, raw"if typo")
}

      
test("wacc_examples/invalid/syntaxErr/literals/charLiteralSingle.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/literals/charLiteralSingle.wacc")).get
  assert(!result.isSuccess, raw"Characters can have at most one character in them!")
}

      
test("wacc_examples/invalid/syntaxErr/literals/stringLiteralNoNewlines.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/literals/stringLiteralNoNewlines.wacc")).get
  assert(!result.isSuccess, raw"Strings cannot be split across lines")
}

      
test("wacc_examples/invalid/syntaxErr/literals/stringLiteralOnlyAscii.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/literals/stringLiteralOnlyAscii.wacc")).get
  assert(!result.isSuccess, raw"Strings must only contain ASCII")
}

      
test("wacc_examples/invalid/syntaxErr/pairs/badLookup01.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/pairs/badLookup01.wacc")).get
  assert(!result.isSuccess, raw"try to directly print pair's first element")
}

      
test("wacc_examples/invalid/syntaxErr/pairs/badLookup02.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/pairs/badLookup02.wacc")).get
  assert(!result.isSuccess, raw"try to directly print pair's second element")
}

      
test("wacc_examples/invalid/syntaxErr/pairs/elemOfNonPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/pairs/elemOfNonPair.wacc")).get
  assert(!result.isSuccess, raw"Trying to get the fst or snd of a non-pair.")
}

      
test("wacc_examples/invalid/syntaxErr/pairs/fstNull.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/pairs/fstNull.wacc")).get
  assert(!result.isSuccess, raw"call fst on a null pair literal")
}

      
test("wacc_examples/invalid/syntaxErr/pairs/noNesting.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/pairs/noNesting.wacc")).get
  assert(!result.isSuccess, raw"pair types cannot be nested")
}

      
test("wacc_examples/invalid/syntaxErr/pairs/sndNull.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/pairs/sndNull.wacc")).get
  assert(!result.isSuccess, raw"call snd on a null pair literal")
}

      
test("wacc_examples/invalid/syntaxErr/print/printlnCharArry.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/print/printlnCharArry.wacc")).get
  assert(!result.isSuccess, raw"You cannot directly print a char[] in WACC")
}

      
test("wacc_examples/invalid/syntaxErr/sequence/doubleSeq.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/sequence/doubleSeq.wacc")).get
  assert(!result.isSuccess, raw"missing sequential composition")
}

      
test("wacc_examples/invalid/syntaxErr/sequence/emptySeq.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/sequence/emptySeq.wacc")).get
  assert(!result.isSuccess, raw"extra sequential composition")
}

      
test("wacc_examples/invalid/syntaxErr/sequence/endSeq.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/sequence/endSeq.wacc")).get
  assert(!result.isSuccess, raw"extra sequential composition ")
}

      
test("wacc_examples/invalid/syntaxErr/sequence/extraSeq.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/sequence/extraSeq.wacc")).get
  assert(!result.isSuccess, raw"extra sequential composition")
}

      
test("wacc_examples/invalid/syntaxErr/sequence/missingSeq.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/sequence/missingSeq.wacc")).get
  assert(!result.isSuccess, raw"missing sequential composition")
}

      
test("wacc_examples/invalid/syntaxErr/variables/badintAssignments.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/variables/badintAssignments.wacc")).get
  assert(!result.isSuccess, raw"bad integer assignments - multiple syntax errors")
}

      
test("wacc_examples/invalid/syntaxErr/variables/badintAssignments1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/variables/badintAssignments1.wacc")).get
  assert(!result.isSuccess, raw"bad integer assignment - whitespace should delimit ints")
}

      
test("wacc_examples/invalid/syntaxErr/variables/badintAssignments2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/variables/badintAssignments2.wacc")).get
  assert(!result.isSuccess, raw"bad integer assignments - malformed int literal")
}

      
test("wacc_examples/invalid/syntaxErr/variables/bigIntAssignment.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/variables/bigIntAssignment.wacc")).get
  assert(!result.isSuccess, raw"int assignment overlow")
}

      
test("wacc_examples/invalid/syntaxErr/variables/varNoName.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/variables/varNoName.wacc")).get
  assert(!result.isSuccess, raw"variable declaration missing variable name")
}

      
test("wacc_examples/invalid/syntaxErr/while/donoErr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/while/donoErr.wacc")).get
  assert(!result.isSuccess, raw"done typo")
}

      
test("wacc_examples/invalid/syntaxErr/while/dooErr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/while/dooErr.wacc")).get
  assert(!result.isSuccess, raw"do typo")
}

      
test("wacc_examples/invalid/syntaxErr/while/whilErr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/while/whilErr.wacc")).get
  assert(!result.isSuccess, raw"while typo")
}

      
test("wacc_examples/invalid/syntaxErr/while/whileNodo.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/while/whileNodo.wacc")).get
  assert(!result.isSuccess, raw"while missing opening do")
}

      
test("wacc_examples/invalid/syntaxErr/while/whileNodone.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/invalid/syntaxErr/while/whileNodone.wacc")).get
  assert(!result.isSuccess, raw"while missing closing done")
}

      
}
