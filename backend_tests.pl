use File::Find;

open($outfile, '>', "./src/test/scala/wacc/GeneratedBackendTests.scala");
print $outfile ("
package wacc

import org.scalatest.Tag
import org.scalatest.matchers.should.Matchers
import java.io.File
import java.io.ByteArrayOutputStream
import java.io.ByteArrayInputStream
import parsley.io.ParseFromIO
import org.scalatest.funsuite.AnyFunSuite
import parsley.Success
import scala.sys.process.{Process}
import java.util.regex.Pattern

class GeneratedBackendTests extends AnyFunSuite with Matchers{
");

foreach (@ARGV) {
  find(\&record_example, $_);
}

print $outfile ("\n}\n");
close($outfile);

sub record_example {
  if (-f) {
    open(EFH, '<', $_);
    $inputting = 0;
    $ouputting = 0;
    while (readline(EFH)) {
      if ($_ =~ /^#( )?Input:/) {
        $inputting = 1;
        $ouputting = 1;
        # $inp = substr($_, 9, -1);
        my $inp = $_;
        $inp =~ s/^#( )?Input: //;
        $inp =~ s/\r//;
        $inp =~ s/\"\"\"/\"\"\" \+ \"\\\"\\\"\\\"\" \+  \"\"\"/g;
        print $outfile ("
test(\"${File::Find::name} Backend\", Tag(\"${File::Find::dir}\")) {
  val Success(ast) = Parser.code.parseFromFile(new File(\"${File::Find::name}\")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = \"\"\"${inp}\"\"\"
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile(\"tempexec\", \"\")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s\"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o \${executable.getPath()} \${assembly.getPath()}\")
      val executor = (Process(s\"qemu-arm -L /usr/arm-linux-gnueabi/ \${executable.getPath()}\") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail(\"Unsuccessful gcc assembling\")
      }
      val execResult = executor.!
");
        last if ($_ =~ /^# Output:/);
      }
      if ($_ =~ /^# Output:/) {
        $ouputting = 1;
        last;
      }
    }
    if ($ouputting > 0) {
      $expectedOutput = "";
      while (readline(EFH)) {
        last if ($_ !~ /^#/);
        last if ($_ =~ /^# Program:/);
        $desc = substr($_, 2, -1);
        $desc =~ s/\r//;
        $desc =~ s/\\E\"\"\"/\"\"\" \+ \"\\\"\\\"\\\"\" \+ \\Q\"\"\"/g;
        $desc =~ s/#addrs#/\\E([0-9a-zA-Z- ]+)\\Q/g;
        $desc =~ s/#runtime_error#/\\Efatal error:(.|\\s)*\\Q/g;
        $expectedOutput = $expectedOutput.$desc.("\n");
      }
      chop($expectedOutput);
      if ($inputting > 0) {
        print $outfile ("
      val expResult: String = \"\"\"\\Q${expectedOutput}\\E\"\"\"
");
      } else {
        print $outfile ("
test(\"${File::Find::name} Backend\", Tag(\"${File::Find::dir}\")) {
  val Success(ast) = Parser.code.parseFromFile(new File(\"${File::Find::name}\")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile(\"tempexec\", \"\")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s\"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o \${executable.getPath()} \${assembly.getPath()}\")
      val executor = Process(s\"qemu-arm -L /usr/arm-linux-gnueabi/ \${executable.getPath()}\") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail(\"Unsuccessful gcc assembling\")
      }
      val execResult = executor.!
      val expResult: String = \"\"\"\\Q${expectedOutput}\\E\"\"\"
");
      }
      $exitting = 0;
      while (readline(EFH)) {
        if ($_ =~ /^# Exit:/) {
          $exitting = 1;
          last;
        }
      }
      if ($exitting > 0) {
        my $expResult = readline(EFH);
        $expResult = substr($expResult, 2, -1);
        print $outfile ("
      val expExit = ${expResult}");
      } else {
        print $outfile ("
      val expExit = 0");
      }
      if ($ouputting > 0) {
        print $outfile ("
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}
");
      }
    }
    close(EFH);
  }
}

