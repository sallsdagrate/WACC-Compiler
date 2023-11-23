
package wacc

import org.scalatest.matchers.should._
import java.io.File
import parsley.io.ParseFromIO
import org.scalatest.funsuite.AnyFunSuite

class SeparateTestsSyntax extends AnyFunSuite with Matchers{
  
test("wacc_examples/valid/IO/IOLoop.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/IOLoop.wacc")).get
  assert(result.isSuccess, raw"simple input/output loop")
}

      
test("wacc_examples/valid/IO/IOSequence.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/IOSequence.wacc")).get
  assert(result.isSuccess, raw"basic input/output sequence")
}

      
test("wacc_examples/valid/IO/print/hashInProgram.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/hashInProgram.wacc")).get
  assert(result.isSuccess, raw"In-line comments and printing #")
}

      
test("wacc_examples/valid/IO/print/multipleStringsAssignment.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/multipleStringsAssignment.wacc")).get
  assert(result.isSuccess, raw"multiple string assignments and comparisons")
}

      
test("wacc_examples/valid/IO/print/print-backspace.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/print-backspace.wacc")).get
  assert(result.isSuccess, raw"simple print statement off a string with a backspace character")
}

      
test("wacc_examples/valid/IO/print/print.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/print.wacc")).get
  assert(result.isSuccess, raw"simple print statement")
}

      
test("wacc_examples/valid/IO/print/printBool.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printBool.wacc")).get
  assert(result.isSuccess, raw"basic Boolean printing")
}

      
test("wacc_examples/valid/IO/print/printChar.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printChar.wacc")).get
  assert(result.isSuccess, raw"basic character printing")
}

      
test("wacc_examples/valid/IO/print/printCharArray.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printCharArray.wacc")).get
  assert(result.isSuccess, raw"printing the contents of a char[] is possible via an intermediate variable")
}

      
test("wacc_examples/valid/IO/print/printCharAsString.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printCharAsString.wacc")).get
  assert(result.isSuccess, raw"character array treated as a string")
}

      
test("wacc_examples/valid/IO/print/printEscChar.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printEscChar.wacc")).get
  assert(result.isSuccess, raw"basic escaped character printing")
}

      
test("wacc_examples/valid/IO/print/printInt.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printInt.wacc")).get
  assert(result.isSuccess, raw"basic Integer printing")
}

      
test("wacc_examples/valid/IO/print/println.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/println.wacc")).get
  assert(result.isSuccess, raw"simple println statement")
}

      
test("wacc_examples/valid/IO/read/echoBigInt.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoBigInt.wacc")).get
  assert(result.isSuccess, raw"echo the user's input int")
}

      
test("wacc_examples/valid/IO/read/echoBigNegInt.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoBigNegInt.wacc")).get
  assert(result.isSuccess, raw"echo the user's input int")
}

      
test("wacc_examples/valid/IO/read/echoChar.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoChar.wacc")).get
  assert(result.isSuccess, raw"echo the user's input char")
}

      
test("wacc_examples/valid/IO/read/echoInt.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoInt.wacc")).get
  assert(result.isSuccess, raw"echo the user's input int")
}

      
test("wacc_examples/valid/IO/read/echoNegInt.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoNegInt.wacc")).get
  assert(result.isSuccess, raw"echo the user's input int")
}

      
test("wacc_examples/valid/IO/read/echoPuncChar.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoPuncChar.wacc")).get
  assert(result.isSuccess, raw"echo the user's input char")
}

      
test("wacc_examples/valid/IO/read/read.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/read.wacc")).get
  assert(result.isSuccess, raw"simple read statement")
}

      
test("wacc_examples/valid/advanced/binarySortTree.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/advanced/binarySortTree.wacc")).get
  assert(result.isSuccess, raw"The program reads n (number of integers), then n integers. After each input, ")
}

      
test("wacc_examples/valid/advanced/hashTable.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/advanced/hashTable.wacc")).get
  assert(result.isSuccess, raw"This program is interactive. We implement a hash table containing integers and we play with it.")
}

      
test("wacc_examples/valid/advanced/ticTacToe.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/advanced/ticTacToe.wacc")).get
  assert(result.isSuccess, raw"This is a program that allows a human to play Tic Tac Toe with a smart AI. ")
}

      
test("wacc_examples/valid/array/array.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/array.wacc")).get
  assert(result.isSuccess, raw"moderate complexity array manipulations")
}

      
test("wacc_examples/valid/array/arrayBasic.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayBasic.wacc")).get
  assert(result.isSuccess, raw"basic array declaration and assignment")
}

      
test("wacc_examples/valid/array/arrayEmpty.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayEmpty.wacc")).get
  assert(result.isSuccess, raw"empty array declaration (seems to error currently)")
}

      
test("wacc_examples/valid/array/arrayIndexMayBeArrayIndex.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayIndexMayBeArrayIndex.wacc")).get
  assert(result.isSuccess, raw"Testing recursive array indexing")
}

      
test("wacc_examples/valid/array/arrayLength.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayLength.wacc")).get
  assert(result.isSuccess, raw"check length of array")
}

      
test("wacc_examples/valid/array/arrayLookup.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayLookup.wacc")).get
  assert(result.isSuccess, raw"check first element of array")
}

      
test("wacc_examples/valid/array/arrayNested.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayNested.wacc")).get
  assert(result.isSuccess, raw"basic array declaration and assignment")
}

      
test("wacc_examples/valid/array/arrayOnHeap.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayOnHeap.wacc")).get
  assert(result.isSuccess, raw"ensures that arrays are heap allocated")
}

      
test("wacc_examples/valid/array/arrayPrint.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayPrint.wacc")).get
  assert(result.isSuccess, raw"print the contents of a simple array")
}

      
test("wacc_examples/valid/array/arraySimple.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arraySimple.wacc")).get
  assert(result.isSuccess, raw"simple array assignment and lookup")
}

      
test("wacc_examples/valid/array/emptyArrayAloneIsFine.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayAloneIsFine.wacc")).get
  assert(result.isSuccess, raw"this is fine")
}

      
test("wacc_examples/valid/array/emptyArrayNextLine.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayNextLine.wacc")).get
  assert(result.isSuccess, raw"This should work just fine")
}

      
test("wacc_examples/valid/array/emptyArrayPrint.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayPrint.wacc")).get
  assert(result.isSuccess, raw"basic array declaration and print after")
}

      
test("wacc_examples/valid/array/emptyArrayReplace.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayReplace.wacc")).get
  assert(result.isSuccess, raw"it should be possible to reassign to an array")
}

      
test("wacc_examples/valid/array/emptyArrayScope.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayScope.wacc")).get
  assert(result.isSuccess, raw"Scoping shouldn't affect arrays")
}

      
test("wacc_examples/valid/array/free.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/free.wacc")).get
  assert(result.isSuccess, raw"Create and free an array")
}

      
test("wacc_examples/valid/array/lenArrayIndex.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/lenArrayIndex.wacc")).get
  assert(result.isSuccess, raw"Tests that array length works on array indexes")
}

      
test("wacc_examples/valid/array/modifyString.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/modifyString.wacc")).get
  assert(result.isSuccess, raw"create and modify string as array of characters")
}

      
test("wacc_examples/valid/array/printRef.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/array/printRef.wacc")).get
  assert(result.isSuccess, raw"basic array (reference) printing")
}

      
test("wacc_examples/valid/basic/exit/exit-1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exit-1.wacc")).get
  assert(result.isSuccess, raw"common error exit statement")
}

      
test("wacc_examples/valid/basic/exit/exitBasic.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitBasic.wacc")).get
  assert(result.isSuccess, raw"basic exit statement")
}

      
test("wacc_examples/valid/basic/exit/exitBasic2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitBasic2.wacc")).get
  assert(result.isSuccess, raw"basic exit statement")
}

      
test("wacc_examples/valid/basic/exit/exitWrap.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitWrap.wacc")).get
  assert(result.isSuccess, raw"exit with wrapped int")
}

      
test("wacc_examples/valid/basic/skip/comment.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/comment.wacc")).get
  assert(result.isSuccess, raw"simple skip program with comment line")
}

      
test("wacc_examples/valid/basic/skip/commentEoF.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/commentEoF.wacc")).get
  assert(result.isSuccess, raw"simple skip program with comment line ended by EoF not EoL")
}

      
test("wacc_examples/valid/basic/skip/commentInLine.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/commentInLine.wacc")).get
  assert(result.isSuccess, raw"simple skip program with in-line comment")
}

      
test("wacc_examples/valid/basic/skip/skip.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/skip.wacc")).get
  assert(result.isSuccess, raw"simple skip program")
}

      
test("wacc_examples/valid/expressions/andExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/andExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating and")
}

      
test("wacc_examples/valid/expressions/andOverOrExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/andOverOrExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating boolean operator precedence")
}

      
test("wacc_examples/valid/expressions/boolCalc.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/boolCalc.wacc")).get
  assert(result.isSuccess, raw"simple boolean calculation")
}

      
test("wacc_examples/valid/expressions/boolExpr1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/boolExpr1.wacc")).get
  assert(result.isSuccess, raw"evaluating a moderately complex boolean expression")
}

      
test("wacc_examples/valid/expressions/charComparisonExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/charComparisonExpr.wacc")).get
  assert(result.isSuccess, raw"detailed battery of character comparison tests")
}

      
test("wacc_examples/valid/expressions/divExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/divExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating integer division")
}

      
test("wacc_examples/valid/expressions/equalsExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating equality")
}

      
test("wacc_examples/valid/expressions/equalsOverAnd.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverAnd.wacc")).get
  assert(result.isSuccess, raw"evaluating equality and boolean operator precedence")
}

      
test("wacc_examples/valid/expressions/equalsOverBool.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverBool.wacc")).get
  assert(result.isSuccess, raw"evaluating equality and boolean operator precedence")
}

      
test("wacc_examples/valid/expressions/equalsOverOr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverOr.wacc")).get
  assert(result.isSuccess, raw"evaluating equality and boolean operator precedence")
}

      
test("wacc_examples/valid/expressions/greaterEqExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/greaterEqExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating greater-than")
}

      
test("wacc_examples/valid/expressions/greaterExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/greaterExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating greater-than")
}

      
test("wacc_examples/valid/expressions/intCalc.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/intCalc.wacc")).get
  assert(result.isSuccess, raw"simple integer calculation")
}

      
test("wacc_examples/valid/expressions/intExpr1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/intExpr1.wacc")).get
  assert(result.isSuccess, raw"evaluating a moderately complex integer expression")
}

      
test("wacc_examples/valid/expressions/lessCharExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessCharExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating less-than on characters")
}

      
test("wacc_examples/valid/expressions/lessEqExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessEqExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating less-than-or-equal-to")
}

      
test("wacc_examples/valid/expressions/lessExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating less-than")
}

      
test("wacc_examples/valid/expressions/longExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr.wacc")).get
  assert(result.isSuccess, raw"tests whether the compiler can handle long expressions")
}

      
test("wacc_examples/valid/expressions/longExpr2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr2.wacc")).get
  assert(result.isSuccess, raw"tests whether the compiler can handle long expressions")
}

      
test("wacc_examples/valid/expressions/longExpr3.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr3.wacc")).get
  assert(result.isSuccess, raw"tests whether the compiler can handle long expressions")
}

      
test("wacc_examples/valid/expressions/longSplitExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longSplitExpr.wacc")).get
  assert(result.isSuccess, raw"tests whether the compiler can handle long expressions with several variables")
}

      
test("wacc_examples/valid/expressions/longSplitExpr2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longSplitExpr2.wacc")).get
  assert(result.isSuccess, raw"tests whether the compiler can handle long expressions with several variables")
}

      
test("wacc_examples/valid/expressions/minusExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating subtraction")
}

      
test("wacc_examples/valid/expressions/minusMinusExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusMinusExpr.wacc")).get
  assert(result.isSuccess, raw"-- should be recognised as two separate symbols")
}

      
test("wacc_examples/valid/expressions/minusNoWhitespaceExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusNoWhitespaceExpr.wacc")).get
  assert(result.isSuccess, raw"subtraction expressions should not be whitespace sensitive")
}

      
test("wacc_examples/valid/expressions/minusPlusExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusPlusExpr.wacc")).get
  assert(result.isSuccess, raw"-+ should be recognised as two separate symbols")
}

      
test("wacc_examples/valid/expressions/modExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/modExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating modulus")
}

      
test("wacc_examples/valid/expressions/multExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/multExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating multiplication")
}

      
test("wacc_examples/valid/expressions/multNoWhitespaceExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/multNoWhitespaceExpr.wacc")).get
  assert(result.isSuccess, raw"multiplication expressions should not be whitespace sensitive")
}

      
test("wacc_examples/valid/expressions/negBothDiv.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negBothDiv.wacc")).get
  assert(result.isSuccess, raw"division of a negative number by a negative number")
}

      
test("wacc_examples/valid/expressions/negBothMod.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negBothMod.wacc")).get
  assert(result.isSuccess, raw"modulus of a negative number by a negative number")
}

      
test("wacc_examples/valid/expressions/negDividendDiv.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDividendDiv.wacc")).get
  assert(result.isSuccess, raw"division of a negative number")
}

      
test("wacc_examples/valid/expressions/negDividendMod.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDividendMod.wacc")).get
  assert(result.isSuccess, raw"modulus of a negative number")
}

      
test("wacc_examples/valid/expressions/negDivisorDiv.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDivisorDiv.wacc")).get
  assert(result.isSuccess, raw"division by a negative number")
}

      
test("wacc_examples/valid/expressions/negDivisorMod.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDivisorMod.wacc")).get
  assert(result.isSuccess, raw"modulus by a negative number")
}

      
test("wacc_examples/valid/expressions/negExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating negation")
}

      
test("wacc_examples/valid/expressions/notExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/notExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating not")
}

      
test("wacc_examples/valid/expressions/notequalsExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/notequalsExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating inequality")
}

      
test("wacc_examples/valid/expressions/orExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/orExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating or")
}

      
test("wacc_examples/valid/expressions/ordAndchrExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/ordAndchrExpr.wacc")).get
  assert(result.isSuccess, raw"evalutaing ord and chr")
}

      
test("wacc_examples/valid/expressions/plusExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating addition")
}

      
test("wacc_examples/valid/expressions/plusMinusExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusMinusExpr.wacc")).get
  assert(result.isSuccess, raw"+- should be recognised as two separate symbols")
}

      
test("wacc_examples/valid/expressions/plusNoWhitespaceExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusNoWhitespaceExpr.wacc")).get
  assert(result.isSuccess, raw"addition expressions should not be whitespace sensitive")
}

      
test("wacc_examples/valid/expressions/plusPlusExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusPlusExpr.wacc")).get
  assert(result.isSuccess, raw"++ should be recognised as two separate symbols")
}

      
test("wacc_examples/valid/expressions/sequentialCount.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/sequentialCount.wacc")).get
  assert(result.isSuccess, raw"simple sequential counting")
}

      
test("wacc_examples/valid/expressions/stringEqualsExpr.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/stringEqualsExpr.wacc")).get
  assert(result.isSuccess, raw"evaluating string equality")
}

      
test("wacc_examples/valid/function/nested_functions/fibonacciFullRec.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fibonacciFullRec.wacc")).get
  assert(result.isSuccess, raw"recursively calculate the nth fibonacci number")
}

      
test("wacc_examples/valid/function/nested_functions/fibonacciRecursive.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fibonacciRecursive.wacc")).get
  assert(result.isSuccess, raw"recursive calculation of the first 20 fibonacci numbers")
}

      
test("wacc_examples/valid/function/nested_functions/fixedPointRealArithmetic.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fixedPointRealArithmetic.wacc")).get
  assert(result.isSuccess, raw"This program implements floating-point type using integers.")
}

      
test("wacc_examples/valid/function/nested_functions/functionConditionalReturn.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/functionConditionalReturn.wacc")).get
  assert(result.isSuccess, raw"program has function which only contains an if statement and a return in each branch")
}

      
test("wacc_examples/valid/function/nested_functions/mutualRecursion.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/mutualRecursion.wacc")).get
  assert(result.isSuccess, raw"a pair of mutually recursive functions")
}

      
test("wacc_examples/valid/function/nested_functions/printInputTriangle.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/printInputTriangle.wacc")).get
  assert(result.isSuccess, raw"print a user-specified sized triangle")
}

      
test("wacc_examples/valid/function/nested_functions/printTriangle.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/printTriangle.wacc")).get
  assert(result.isSuccess, raw"print a fixed size triangle")
}

      
test("wacc_examples/valid/function/nested_functions/simpleRecursion.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/simpleRecursion.wacc")).get
  assert(result.isSuccess, raw"a simple recursive function")
}

      
test("wacc_examples/valid/function/simple_functions/argScopeCanBeShadowed.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/argScopeCanBeShadowed.wacc")).get
  assert(result.isSuccess, raw"Arguments can be shadowed by the function body")
}

      
test("wacc_examples/valid/function/simple_functions/asciiTable.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/asciiTable.wacc")).get
  assert(result.isSuccess, raw"print out a lookup table for Ascii character representations")
}

      
test("wacc_examples/valid/function/simple_functions/functionDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionDeclaration.wacc")).get
  assert(result.isSuccess, raw"a simple function is declared, but not called")
}

      
test("wacc_examples/valid/function/simple_functions/functionDoubleReturn.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionDoubleReturn.wacc")).get
  assert(result.isSuccess, raw"a simple function with two back-to-back returns at the end.")
}

      
test("wacc_examples/valid/function/simple_functions/functionIfReturns.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionIfReturns.wacc")).get
  assert(result.isSuccess, raw"a simple function with nested returns inside an if-statement after a return")
}

      
test("wacc_examples/valid/function/simple_functions/functionManyArguments.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionManyArguments.wacc")).get
  assert(result.isSuccess, raw"a function with varied inputs")
}

      
test("wacc_examples/valid/function/simple_functions/functionMultiReturns.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionMultiReturns.wacc")).get
  assert(result.isSuccess, raw"a simple function with multiple returns, importantly one at the end.")
}

      
test("wacc_examples/valid/function/simple_functions/functionReturnPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionReturnPair.wacc")).get
  assert(result.isSuccess, raw"creates a pair which is returned from a function")
}

      
test("wacc_examples/valid/function/simple_functions/functionSimple.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionSimple.wacc")).get
  assert(result.isSuccess, raw"a simple function definition and call")
}

      
test("wacc_examples/valid/function/simple_functions/functionSimpleLoop.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionSimpleLoop.wacc")).get
  assert(result.isSuccess, raw"define and call a function with a simple loop")
}

      
test("wacc_examples/valid/function/simple_functions/functionUpdateParameter.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionUpdateParameter.wacc")).get
  assert(result.isSuccess, raw"test that the passed parameter can be updated and used")
}

      
test("wacc_examples/valid/function/simple_functions/incFunction.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/incFunction.wacc")).get
  assert(result.isSuccess, raw"a simple increment function definition and usage")
}

      
test("wacc_examples/valid/function/simple_functions/manyArgumentsChar.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/manyArgumentsChar.wacc")).get
  assert(result.isSuccess, raw"tests a function with more arguments than fit into registers")
}

      
test("wacc_examples/valid/function/simple_functions/manyArgumentsInt.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/manyArgumentsInt.wacc")).get
  assert(result.isSuccess, raw"tests a function with more arguments than fit into registers")
}

      
test("wacc_examples/valid/function/simple_functions/negFunction.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/negFunction.wacc")).get
  assert(result.isSuccess, raw"a simple negation function definition and usage")
}

      
test("wacc_examples/valid/function/simple_functions/punning.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/punning.wacc")).get
  assert(result.isSuccess, raw"Functions should be able to have the same name as variables")
}

      
test("wacc_examples/valid/function/simple_functions/sameArgName.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameArgName.wacc")).get
  assert(result.isSuccess, raw"program with function that has same parameter name as function")
}

      
test("wacc_examples/valid/function/simple_functions/sameArgName2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameArgName2.wacc")).get
  assert(result.isSuccess, raw"program with function that has same parameter name as function")
}

      
test("wacc_examples/valid/function/simple_functions/sameNameAsVar.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameNameAsVar.wacc")).get
  assert(result.isSuccess, raw"program with function that has same name as a variable")
}

      
test("wacc_examples/valid/function/simple_functions/usesArgumentWhilstMakingArgument.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/usesArgumentWhilstMakingArgument.wacc")).get
  assert(result.isSuccess, raw"tests a function with more arguments than fit into registers")
}

      
test("wacc_examples/valid/if/if1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if1.wacc")).get
  assert(result.isSuccess, raw"Simple conditional statement with int comparison test")
}

      
test("wacc_examples/valid/if/if2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if2.wacc")).get
  assert(result.isSuccess, raw"Simple conditional statement with int comparison test")
}

      
test("wacc_examples/valid/if/if3.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if3.wacc")).get
  assert(result.isSuccess, raw"Simple conditional statement with int comparison test")
}

      
test("wacc_examples/valid/if/if4.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if4.wacc")).get
  assert(result.isSuccess, raw"Simple conditional statement with boolen expression test")
}

      
test("wacc_examples/valid/if/if5.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if5.wacc")).get
  assert(result.isSuccess, raw"Simple conditional statement with boolen expression test")
}

      
test("wacc_examples/valid/if/if6.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if6.wacc")).get
  assert(result.isSuccess, raw"Simple conditional statement with character comparison test")
}

      
test("wacc_examples/valid/if/ifBasic.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifBasic.wacc")).get
  assert(result.isSuccess, raw"simple if statement")
}

      
test("wacc_examples/valid/if/ifFalse.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifFalse.wacc")).get
  assert(result.isSuccess, raw"simple false if statement")
}

      
test("wacc_examples/valid/if/ifTrue.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifTrue.wacc")).get
  assert(result.isSuccess, raw"simple true if statement")
}

      
test("wacc_examples/valid/if/whitespace.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/if/whitespace.wacc")).get
  assert(result.isSuccess, raw"Whitespace only important between keyword and variable tokens")
}

      
test("wacc_examples/valid/pairs/checkRefPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/checkRefPair.wacc")).get
  assert(result.isSuccess, raw"create a pair(int, char) with a second reference to it and check it works")
}

      
test("wacc_examples/valid/pairs/createPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair.wacc")).get
  assert(result.isSuccess, raw"create a pair (int, int)")
}

      
test("wacc_examples/valid/pairs/createPair02.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair02.wacc")).get
  assert(result.isSuccess, raw"create a pair (char, char)")
}

      
test("wacc_examples/valid/pairs/createPair03.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair03.wacc")).get
  assert(result.isSuccess, raw"create a pair (int, char)")
}

      
test("wacc_examples/valid/pairs/createRefPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createRefPair.wacc")).get
  assert(result.isSuccess, raw"create a pair(int, char) with a second reference to it")
}

      
test("wacc_examples/valid/pairs/free.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/free.wacc")).get
  assert(result.isSuccess, raw"Create and free a simple pair")
}

      
test("wacc_examples/valid/pairs/linkedList.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/linkedList.wacc")).get
  assert(result.isSuccess, raw"create and print a linked list using pairs")
}

      
test("wacc_examples/valid/pairs/nestedPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPair.wacc")).get
  assert(result.isSuccess, raw"create a pair (int, pair (int, int) )")
}

      
test("wacc_examples/valid/pairs/nestedPairLeftAssign.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPairLeftAssign.wacc")).get
  assert(result.isSuccess, raw"nested pair assignments are legal as long as the right hand-side type is known")
}

      
test("wacc_examples/valid/pairs/nestedPairRightExtract.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPairRightExtract.wacc")).get
  assert(result.isSuccess, raw"nested pair extractions are legal as long as the left hand-side type is known")
}

      
test("wacc_examples/valid/pairs/null.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/null.wacc")).get
  assert(result.isSuccess, raw"null pair assignment")
}

      
test("wacc_examples/valid/pairs/pairarray.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/pairarray.wacc")).get
  assert(result.isSuccess, raw"ensures that pairs can be unpacked directly from arrays")
}

      
test("wacc_examples/valid/pairs/printNull.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printNull.wacc")).get
  assert(result.isSuccess, raw"print the null reference")
}

      
test("wacc_examples/valid/pairs/printNullPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printNullPair.wacc")).get
  assert(result.isSuccess, raw"print pair a null pair")
}

      
test("wacc_examples/valid/pairs/printPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printPair.wacc")).get
  assert(result.isSuccess, raw"print pair program")
}

      
test("wacc_examples/valid/pairs/printPairOfNulls.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printPairOfNulls.wacc")).get
  assert(result.isSuccess, raw"print a pair of null pairs")
}

      
test("wacc_examples/valid/pairs/readPair.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/readPair.wacc")).get
  assert(result.isSuccess, raw"construct a pair from supplied user input")
}

      
test("wacc_examples/valid/pairs/writeFst.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/writeFst.wacc")).get
  assert(result.isSuccess, raw"create a pair and write to its first element")
}

      
test("wacc_examples/valid/pairs/writeSnd.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/writeSnd.wacc")).get
  assert(result.isSuccess, raw"create a pair and write to its second element")
}

      
test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc")).get
  assert(result.isSuccess, raw"attempt out of bounds array access (this ought to seg fault or similar)")
}

      
test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc")).get
  assert(result.isSuccess, raw"attempt out of bounds array access (this ought to seg fault or similar)")
}

      
test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc")).get
  assert(result.isSuccess, raw"attempt write out of array bounds (gods, this really should not work!)")
}

      
test("wacc_examples/valid/runtimeErr/divideByZero/divZero.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/divZero.wacc")).get
  assert(result.isSuccess, raw"division by zero")
}

      
test("wacc_examples/valid/runtimeErr/divideByZero/divideByZero.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/divideByZero.wacc")).get
  assert(result.isSuccess, raw"attempt divide by zero")
}

      
test("wacc_examples/valid/runtimeErr/divideByZero/modByZero.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/modByZero.wacc")).get
  assert(result.isSuccess, raw"attempt modulo by zero")
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intJustOverflow.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intJustOverflow.wacc")).get
  assert(result.isSuccess, raw"integer overflow")
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intUnderflow.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intUnderflow.wacc")).get
  assert(result.isSuccess, raw"integer underflow")
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intWayOverflow.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intWayOverflow.wacc")).get
  assert(result.isSuccess, raw"positive overflow")
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intmultOverflow.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intmultOverflow.wacc")).get
  assert(result.isSuccess, raw"integer overflow - generates odd assembly error!")
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc")).get
  assert(result.isSuccess, raw"negating biggest possible negative integer")
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc")).get
  assert(result.isSuccess, raw"multiplying the biggest possible negative integer")
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc")).get
  assert(result.isSuccess, raw"multiplying a negative integer")
}

      
test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc")).get
  assert(result.isSuccess, raw"negative overflow")
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/freeNull.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/freeNull.wacc")).get
  assert(result.isSuccess, raw"Create and free a null pair")
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/readNull1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/readNull1.wacc")).get
  assert(result.isSuccess, raw"attempt dereference of a null pair by reading into an element of it")
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/readNull2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/readNull2.wacc")).get
  assert(result.isSuccess, raw"attempt dereference of a null pair by reading into an element of it")
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/setNull1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/setNull1.wacc")).get
  assert(result.isSuccess, raw"attempt dereference of a null pair by setting an element of it")
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/setNull2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/setNull2.wacc")).get
  assert(result.isSuccess, raw"attempt dereference of a null pair by setting an element of it")
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/useNull1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/useNull1.wacc")).get
  assert(result.isSuccess, raw"attempt dereference of a null pair by using it")
}

      
test("wacc_examples/valid/runtimeErr/nullDereference/useNull2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/useNull2.wacc")).get
  assert(result.isSuccess, raw"attempt dereference of a null pair by using it")
}

      
test("wacc_examples/valid/scope/ifNested1.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/ifNested1.wacc")).get
  assert(result.isSuccess, raw"Nested conditional statement")
}

      
test("wacc_examples/valid/scope/ifNested2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/ifNested2.wacc")).get
  assert(result.isSuccess, raw"Deeply nested conditional statement")
}

      
test("wacc_examples/valid/scope/indentationNotImportant.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/indentationNotImportant.wacc")).get
  assert(result.isSuccess, raw"consistent indentation is for readability purposes only")
}

      
test("wacc_examples/valid/scope/intsAndKeywords.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/intsAndKeywords.wacc")).get
  assert(result.isSuccess, raw"checking handling of ints and keywords")
}

      
test("wacc_examples/valid/scope/printAllTypes.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/printAllTypes.wacc")).get
  assert(result.isSuccess, raw"Tests scoping with most variable types")
}

      
test("wacc_examples/valid/scope/scope.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scope.wacc")).get
  assert(result.isSuccess, raw"simple scoping test")
}

      
test("wacc_examples/valid/scope/scopeBasic.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeBasic.wacc")).get
  assert(result.isSuccess, raw"very simple scoping test")
}

      
test("wacc_examples/valid/scope/scopeIfRedefine.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeIfRedefine.wacc")).get
  assert(result.isSuccess, raw"variable scoping test that redefines a variable within an if-statement")
}

      
test("wacc_examples/valid/scope/scopeRedefine.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeRedefine.wacc")).get
  assert(result.isSuccess, raw"more complex variable scoping test that redefines a variable")
}

      
test("wacc_examples/valid/scope/scopeSimpleRedefine.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeSimpleRedefine.wacc")).get
  assert(result.isSuccess, raw"variable scoping test that redefines a variable")
}

      
test("wacc_examples/valid/scope/scopeVars.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeVars.wacc")).get
  assert(result.isSuccess, raw"simple variable scoping test")
}

      
test("wacc_examples/valid/scope/scopeWhileNested.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeWhileNested.wacc")).get
  assert(result.isSuccess, raw"variable scoping nested within a while-loop")
}

      
test("wacc_examples/valid/scope/scopeWhileRedefine.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeWhileRedefine.wacc")).get
  assert(result.isSuccess, raw"variable scoping test that redefines a variable within a while-loop")
}

      
test("wacc_examples/valid/scope/splitScope.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/splitScope.wacc")).get
  assert(result.isSuccess, raw"splits the first appearances of variables by a new scope to ensure proper grouping")
}

      
test("wacc_examples/valid/sequence/basicSeq.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/basicSeq.wacc")).get
  assert(result.isSuccess, raw"basic sequential composition")
}

      
test("wacc_examples/valid/sequence/basicSeq2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/basicSeq2.wacc")).get
  assert(result.isSuccess, raw"basic sequential composition")
}

      
test("wacc_examples/valid/sequence/boolAssignment.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/boolAssignment.wacc")).get
  assert(result.isSuccess, raw"simple boolean variable declaration and assignment")
}

      
test("wacc_examples/valid/sequence/charAssignment.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/charAssignment.wacc")).get
  assert(result.isSuccess, raw"simple character variable declaration and assignment")
}

      
test("wacc_examples/valid/sequence/exitSimple.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/exitSimple.wacc")).get
  assert(result.isSuccess, raw"exit with unreachable print")
}

      
test("wacc_examples/valid/sequence/intAssignment.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/intAssignment.wacc")).get
  assert(result.isSuccess, raw"simple integer variable declaration and assignment")
}

      
test("wacc_examples/valid/sequence/intLeadingZeros.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/intLeadingZeros.wacc")).get
  assert(result.isSuccess, raw"integer variable declaration with leading zeroes")
}

      
test("wacc_examples/valid/sequence/stringAssignment.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/stringAssignment.wacc")).get
  assert(result.isSuccess, raw"simple string variable declaration and assignment")
}

      
test("wacc_examples/valid/variables/_VarNames.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/_VarNames.wacc")).get
  assert(result.isSuccess, raw"variable can have _ in their names")
}

      
test("wacc_examples/valid/variables/boolDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/boolDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple boolean variable declaration")
}

      
test("wacc_examples/valid/variables/boolDeclaration2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/boolDeclaration2.wacc")).get
  assert(result.isSuccess, raw"simple true boolean variable declaration")
}

      
test("wacc_examples/valid/variables/capCharDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/capCharDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple capital character variable declaration")
}

      
test("wacc_examples/valid/variables/charDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/charDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple character variable declaration")
}

      
test("wacc_examples/valid/variables/charDeclaration2.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/charDeclaration2.wacc")).get
  assert(result.isSuccess, raw"simple character variable declaration")
}

      
test("wacc_examples/valid/variables/emptyStringDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/emptyStringDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple empty string variable declaration")
}

      
test("wacc_examples/valid/variables/intDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/intDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple integer variable declaration")
}

      
test("wacc_examples/valid/variables/longVarNames.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/longVarNames.wacc")).get
  assert(result.isSuccess, raw"variable can have very long names")
}

      
test("wacc_examples/valid/variables/manyVariables.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/manyVariables.wacc")).get
  assert(result.isSuccess, raw"creates 257 variables")
}

      
test("wacc_examples/valid/variables/negIntDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/negIntDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple negative integer variable declaration")
}

      
test("wacc_examples/valid/variables/puncCharDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/puncCharDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple punctuation character variable declaration")
}

      
test("wacc_examples/valid/variables/stringCarriageReturn.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/stringCarriageReturn.wacc")).get
  assert(result.isSuccess, raw"carriage returns should be parsable, but their behaviour is non-portable, so the IO is not currently tested")
}

      
test("wacc_examples/valid/variables/stringDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/stringDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple string variable declaration")
}

      
test("wacc_examples/valid/variables/zeroIntDeclaration.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/zeroIntDeclaration.wacc")).get
  assert(result.isSuccess, raw"simple zero integer variable declaration")
}

      
test("wacc_examples/valid/while/fibonacciFullIt.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/fibonacciFullIt.wacc")).get
  assert(result.isSuccess, raw"iteratively calculate the given fibonacci number")
}

      
test("wacc_examples/valid/while/fibonacciIterative.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/fibonacciIterative.wacc")).get
  assert(result.isSuccess, raw"iterative calculation of the first 20 fibonacci numbers")
}

      
test("wacc_examples/valid/while/loopCharCondition.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/loopCharCondition.wacc")).get
  assert(result.isSuccess, raw"Use a character as a loop condition. Enter the loop once only, then exit the loop.")
}

      
test("wacc_examples/valid/while/loopIntCondition.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/loopIntCondition.wacc")).get
  assert(result.isSuccess, raw"Use an integer as a loop condition. Enter the loop once only, then exit the loop.")
}

      
test("wacc_examples/valid/while/max.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/max.wacc")).get
  assert(result.isSuccess, raw"find the max of two numbers")
}

      
test("wacc_examples/valid/while/min.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/min.wacc")).get
  assert(result.isSuccess, raw"find the min of two numbers")
}

      
test("wacc_examples/valid/while/rmStyleAdd.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/rmStyleAdd.wacc")).get
  assert(result.isSuccess, raw"register machine style addition")
}

      
test("wacc_examples/valid/while/rmStyleAddIO.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/rmStyleAddIO.wacc")).get
  assert(result.isSuccess, raw"register machine style addition")
}

      
test("wacc_examples/valid/while/whileBasic.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileBasic.wacc")).get
  assert(result.isSuccess, raw"simple while loop")
}

      
test("wacc_examples/valid/while/whileBoolFlip.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileBoolFlip.wacc")).get
  assert(result.isSuccess, raw"while loop flips bool to terminate")
}

      
test("wacc_examples/valid/while/whileCount.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileCount.wacc")).get
  assert(result.isSuccess, raw"simple counting while loop")
}

      
test("wacc_examples/valid/while/whileFalse.wacc") {
  val result = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileFalse.wacc")).get
  assert(result.isSuccess, raw"simple unentered while loop")
}

      
}
