package wacc
import parsley.errors.DefaultErrorBuilder
import parsley.errors.tokenextractors


class SyntaxErrorBuilder extends DefaultErrorBuilder with tokenextractors.MatchParserDemand {
    override def format(pos: Position, source: Source, lines: ErrorInfoLines): String = "syntax error: " + super.format(pos, source, lines)
    //override def pos(line: Int, col: Int): Position = ???

// basic/bgnErr (parses as "bgn s" not unexpected identifier "bgn")
// basic/BeginNoend (eof vs eoi)
// basic/multipleBegins (parses begin as "B")
// function/badlyNamed is doomed
// funcExpr should explain function calls, say expected index
// explanation ^:  function calls may not appear in expressions and must use `call`
// functionConditionalNoReturn is nowhere near correct
}

