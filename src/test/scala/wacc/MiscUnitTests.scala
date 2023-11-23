
package wacc

import parsley.{Failure, Success}
import wacc.Semantic.semanticAnalysis
import org.scalatest.matchers.should.Matchers
import org.scalatest.funsuite.AnyFunSuite


class MiscUnitTest extends AnyFunSuite with Matchers{

  test("does the data count go up") {
    val d1 = new Datum("hello")
    val d2 = new Datum("world")
    val d3 = new Datum("elephant")
    val d4 = new Datum("dog")
    assert(d1.aCount + 3 == d4.aCount)
    assert(d2.aCount + 1 == d3.aCount)
  }

  test("no unnecessary routines") {
    val routineLessWacc = """
    begin
    skip
    end
    """
    lexer.lex.lexeme(Parser.code).parse(routineLessWacc) match {
        case Success(x) => {
          semanticAnalysis(x) match {
            case ProgramAndTable(_, _) => {
              val ir = Generator.generate(x)
              assert(ir.templates.values.filter(!_).isEmpty)
            }
            case Errors(errors) =>
              fail("valid wacc was rejected")
          }
        }
        case Failure(msg) => {
          fail("valid wacc was rejected")
        }
      }
  }

}
