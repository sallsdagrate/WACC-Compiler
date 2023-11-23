package wacc

import parsley.Parsley
import parsley.Parsley.{attempt, lookAhead}
import parsley.combinator._
import parsley.expr._
import parsley.lift.lift2
import parsley.errors.combinator._

object Parser {

  import ast._
  import lexer.implicits.implicitSymbol

  lazy val `<program>`: Parsley[Program] = Program("begin" ~> many(`<func>`), `<stat>` <~ "end")

  lazy val `<func>`: Parsley[Func] = attempt(lookAhead(`<typeident>` <~> "(")) ~> Func(`<typeident>`, "(" ~> lexer.lex.lexeme.separators.commaSep(`<param>`) <~ ")", "is" ~> `<funcstat>` <~ "end").label("funciton declaration")
  lazy val `<funcstat>`: Parsley[Stats] = Stats(lift2((x: List[Stat], y: Stat) => x.appended(y), many(attempt(`<line>` <~ ";")),
    Return("return" ~> `<expr>`) <|> Exit("exit" ~> `<expr>`) <|> `<funcreturnif>` <|> Scope("begin" ~> `<funcstat>` <~ "end")))
  lazy val `<statvalidreturn>`: Parsley[Stats] = Stats(lexer.lex.lexeme.separators.semiSep1(`<line>`))
  lazy val `<funcreturnif>`: Parsley[If] = If("if" ~> `<expr>`, "then" ~> `<funcstat>`, "else" ~> `<funcstat>` <~ "fi")

  lazy val `<stat>`: Parsley[Stats] = Stats(lexer.lex.lexeme.separators.semiSep1(`<line>`))

  lazy val `<line>` : Parsley[Stat] =
      Return("return" ~> `<expr>`) <|>
      Scope("begin" ~> `<stat>` <~ "end") <|>
      (Skip <# "skip") <|>
      Read("read" ~> `<lvalue>`) <|>
      Free("free" ~> `<expr>`) <|>
      Exit("exit" ~> `<expr>`) <|>
      Println("println" ~> `<expr>`) <|>
      Print("print" ~> `<expr>`) <|>
      `<assignnew>` <|>
      `<assign>` <|>
      If("if" ~> `<expr>`, "then" ~> `<stat>`, "else".explain("all if statements must have an else clause") ~> `<stat>` <~ "fi".explain("unclosed if statement")) <|>
      While("while" ~> `<expr>`, "do" ~> `<stat>` <~ "done".explain("unclosed while loop"))

  lazy val `<assignnew>`: Parsley[AssignNew] = AssignNew(`<typeident>`,"=" ~> `<rvalue>`)

  lazy val `<assign>`: Parsley[Assign] = Assign(`<lvalue>`, ("=").label("assignment") ~> `<rvalue>`)

  lazy val `<atom>`: Parsley[Expr] =
      `<intliter>` <|>
      IdentOrArrayElem(`<ident>`, many(("[").label("index").explain("like `xs[idx]`") ~> `<expr>`<~ "]")) <|>
      `<boolliter>` <|>
      `<charliter>` <|>
      `<strliter>` <|>
      `<pairliter>` <|>
      `<unopp>`

  lazy val `<intliter>`: Parsley[IntLiter] = IntLiter(lexer.integers).label("integer literal")
  lazy val `<boolliter>`: Parsley[BoolLiter] = BoolLiter("true" #> true <|> "false" #> false).label("boolean literal")
  lazy val `<charliter>`: Parsley[CharLiter] = CharLiter(lexer.characterLiteral).label("character literal")
  lazy val `<strliter>`: Parsley[StrLiter] = StrLiter(lexer.stringLiteral).label("string literal")
  lazy val `<pairliter>`: Parsley[Expr] = (PairLiter <# "null").label("null")
  lazy val `<ident>`: Parsley[Ident] = Ident(lexer.ident)
  lazy val `<unopp>`: Parsley[UnOpp] =
      (Not("!" ~> `<unoppexpr>`) <|>
      Negate("-" ~> `<unoppexpr>`) <|>
      Len("len" ~> `<unoppexpr>`) <|>
      Ord("ord" ~> `<unoppexpr>`) <|>
      Chr("chr" ~> `<unoppexpr>`)).label("unary operator")
  
  lazy val `<expr>` : Parsley[Expr] =
    precedence(`<atom>`, ("(") ~> (`<expr>`) <~ ")")(
      Ops(InfixL)(Mul <# "*".label("binary operator"), Div <# "/".label("binary operator"), Mod <# "%".label("binary operator")),
      Ops(InfixL)(Add <# "+".label("binary operator"), Sub <# "-".label("binary operator")),
      Ops(InfixN)(GTE <# ">=".label("binary operator"), LTE <# "<=".label("binary operator"), GT <# ">".label("binary operator"), LT <# "<".label("binary operator")),
      Ops(InfixN)(Eq <# "==".label("binary operator"), NEq <# "!=".label("binary operator")),
      Ops(InfixR)(And <# "&&".label("binary operator")),
      Ops(InfixR)(Or <# "||".label("binary operator"))
    )

      


  lazy val `<unoppexpr>` : Parsley[Expr] = `<atom>` <|> ("(" ~> `<expr>` <~ ")")
    

  lazy val `<lvalue>` : Parsley[LValue] = IdentOrArrayElem(`<ident>`, many(("[").label("index").explain("like `xs[idx]`") ~> `<expr>`<~ "]")) <|> `<pairelem>`

  lazy val `<rvalue>`: Parsley[RValue] = `<expr>` <|> `<arrayliter>` <|> `<newpair>` <|> `<pairelem>` <|> `<call>`

  lazy val `<type>`: Parsley[Type] = chain.postfix(`<basetype>` <|> `<pairtype>`, ArrayType <# ("[" <~> "]"))

  lazy val `<pairelem>`: Parsley[LValue with RValue] = PairElemFst("fst" ~> `<lvalue>`) <|>
      PairElemSnd("snd" ~> `<lvalue>`)

  lazy val `<arrayliter>`: Parsley[ArrayLiter] = ArrayLiter("[" ~> lexer.lex.lexeme.separators.commaSep(`<expr>`) <~ "]").label("array literal")

  lazy val `<arglist>`: Parsley[ArgList] = ArgList(lexer.lex.lexeme.separators.commaSep(`<expr>`))

  lazy val `<newpair>`: Parsley[NewPair] = NewPair("newpair" ~> "(" ~> `<expr>`, "," ~> `<expr>` <~ ")").label("pair literal")

  lazy val `<call>`: Parsley[Call] = Call("call" ~> `<ident>`, "(" ~> `<arglist>` <~ ")").label("function call")

  lazy val `<basetype>` : Parsley[Type_] = ((IntT <# "int") <|> (BoolT <# "bool") <|> (CharT <# "char") <|> (StringT <# "string")).label("type")

  lazy val `<pairtype>`: Parsley[PairType] = (PairType("pair" ~> "(" ~> `<pairelemtype>`, "," ~> `<pairelemtype>` <~ ")")).label("type")

  lazy val `<pairelemtype>`: Parsley[PairElemType] =  chain.postfix(PairT <# "pair"<|> `<basetype>`, ArrayType <# ("[" <~> "]"))

  lazy val `<param>`: Parsley[Param] = Param(`<typeident>`)

  lazy val `<typeident>`: Parsley[TypeIdent] = TypeIdent(`<type>`, `<ident>`)


  lazy val code: Parsley[Program] = lexer.lex.fully(`<program>`)

}

