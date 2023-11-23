use File::Find;

open($outfile, '>', "./src/test/scala/wacc/SeparateTestsSemantic.scala");
print $outfile ("
package wacc

import org.scalatest.matchers.should._
import java.io.File
import parsley.io.ParseFromIO
import org.scalatest.funsuite.AnyFunSuite
import parsley.Success

class SeparateTestsSemantic extends AnyFunSuite with Matchers{
  ");
find(\&record_example, $ARGV[0]);
print $outfile ("\n}\n");
close($outfile);

sub record_example {
  if (-f) {
    open(EFH, '<', $_);
    $desc = substr(readline(EFH), 2, -1);
    $desc =~ s/\r//;
    print $outfile ("
test(\"${File::Find::name}\") {
  val Success(ast) = Parser.code.parseFromFile(new File(\"${File::Find::name}\")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) =>
    case _ => assert(false)
  }
}

      ");
    close(EFH);
  }
}

