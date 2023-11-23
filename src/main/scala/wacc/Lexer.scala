package wacc

import parsley.token.predicate

object lexer {
    import parsley.Parsley
    import parsley.token.Lexer
    import parsley.token.descriptions._


    val desc = LexicalDesc.plain.copy (
        symbolDesc = SymbolDesc.plain.copy(
            hardKeywords = Set("begin","skip","end", "null", "print", "println", "exit",
                "free", "read", "if", "then", "else", "fi", "while", "do", "done", "call",
                "fst", "snd", "newpair", "int", "bool", "char", "string", "pair", "len",
                "ord", "chr", "true", "false", "return"),
            hardOperators = Set("+","-","*","/","%",">=",">","<=","<","==","!=","||","&&","=","!")
        ),
        nameDesc = NameDesc.plain.copy(
            identifierStart = predicate.Basic(c => Character.isLetter(c) || c == '_'),
            identifierLetter = predicate.Basic(c => Character.isLetterOrDigit(c) || c == '_')
        ),
        spaceDesc = SpaceDesc.plain.copy(
            commentLine = "#"
        ),
        numericDesc = numeric.NumericDesc.plain.copy(
            integerNumbersCanBeHexadecimal = false,
            integerNumbersCanBeOctal = false
        ),
        textDesc = text.TextDesc.plain.copy(
            escapeSequences = text.EscapeDesc.plain.copy(
                escBegin = '\\',
                literals = Set('\\'),
                singleMap = Map('0' -> 0x00,
                    'b' -> 0x08,
                    't' -> 0x09,
                    'n' -> 0x0a,
                    'f' -> 0x0c,
                    'r' -> 0x0d,
                    '"' -> 0x22,
                    '\'' -> 0x27)
            ),
            graphicCharacter = predicate.Basic(c => {
                (c != '"') && (c != '\'') && (c != '\\') && (c != '\n') && (c != '\t') && (c != '\r') && (c != '\f') && (c != '\b')
            })
        )
    )

    val lex = new Lexer(desc)

    def fully[A](p: Parsley[A]) = lex.fully(p)
    val implicits = lex.lexeme.symbol.implicits
    val integers = lex.lexeme.numeric.integer.decimal32
    val ident = lex.lexeme.names.identifier
    val characterLiteral = lex.lexeme.text.character.ascii
    val stringLiteral = lex.lexeme.text.string.ascii
}
