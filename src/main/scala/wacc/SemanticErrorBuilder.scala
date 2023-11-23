package wacc

class  SemanticErrorBuilder(
  var semant: String, val position: (Int, Int)
) {

  def message(): String = {
    val (row, col) = position
    return ("Semantic error: " + semant + " at column " + row + " row " + row)
  }

  def addInfo(info: String): Unit = {
    semant + (" " + info)
  }
}
