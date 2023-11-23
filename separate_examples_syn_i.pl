use File::Find;

open($outfile, '>', "./src/test/scala/wacc/SeparateTestsSynI.scala");
print $outfile ("
package wacc

import org.scalatest.matchers.should._
import java.io.File
import parsley.io.ParseFromIO
import org.scalatest.funsuite.AnyFunSuite

class SeparateTestsSynI extends AnyFunSuite with Matchers{
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
  val result = Parser.code.parseFromFile(new File(\"${File::Find::name}\")).get
  assert(!result.isSuccess, raw\"${desc}\")
}

      ");
    close(EFH);
  }
}

