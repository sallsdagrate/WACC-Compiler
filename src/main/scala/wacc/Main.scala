package wacc

import parsley.{Failure, Success}
import wacc.Semantic.semanticAnalysis
import java.io.File

  object Main {

    
    def main(args: Array[String]): Unit = {
      val inFile = new File(args.head)
      val src = scala.io.Source.fromFile(inFile)
      val prog = src.mkString
      src.close()
      lexer.lex.lexeme(Parser.code).parse(prog) match {
        case Success(x) => {
          semanticAnalysis(x) match {
            case ProgramAndTable(_, _) => {
              Writer.write(Generator.generate(x), inFile.getName().dropRight(5))
              sys.exit(0)
            }
            case Errors(errors) =>
              for (error <- errors){
                Console.err.println(error.message());
              }
              sys.exit(200)
          }
        }
        case Failure(msg) => {
          Console.err.println(msg)
          sys.exit(100)
        }
      }
  }
}
