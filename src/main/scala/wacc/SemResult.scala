package wacc


sealed trait SemResult
case class ProgramAndTable(prog: ast.Program, topSt: SemanticTools.SymbolTable) extends SemResult
case class Errors(errors: Array[SemanticErrorBuilder]) extends SemResult