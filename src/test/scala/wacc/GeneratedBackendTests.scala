
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

test("wacc_examples/valid/IO/IOLoop.wacc Backend", Tag("wacc_examples/valid/IO")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/IOLoop.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """1 Y 2 Y 3 Y 4 Y 5 Y 142 N
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\QPlease input an integer: echo input: 1
Do you want to continue entering input?
(enter Y for 'yes' and N for 'no')
Please input an integer: echo input: 2
Do you want to continue entering input?
(enter Y for 'yes' and N for 'no')
Please input an integer: echo input: 3
Do you want to continue entering input?
(enter Y for 'yes' and N for 'no')
Please input an integer: echo input: 4
Do you want to continue entering input?
(enter Y for 'yes' and N for 'no')
Please input an integer: echo input: 5
Do you want to continue entering input?
(enter Y for 'yes' and N for 'no')
Please input an integer: echo input: 142
Do you want to continue entering input?
(enter Y for 'yes' and N for 'no')
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/IOSequence.wacc Backend", Tag("wacc_examples/valid/IO")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/IOSequence.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """37
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\QPlease input an integer: You input: 37
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/hashInProgram.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/hashInProgram.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QWe can print the hash character: #
We can also print # when its in a string.
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/multipleStringsAssignment.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/multipleStringsAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qs1 is Hi
s2 is Hello
They are not the same string.
Now make s1 = s2
s1 is Hello
s2 is Hello
They are the same string.
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/print-backspace.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/print-backspace.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QHello World!
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/print.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/print.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QHello World!\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/printBool.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printBool.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QTrue is true
False is false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/printChar.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QA simple character example is f
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/printCharArray.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printCharArray.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qhi!
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/printCharAsString.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printCharAsString.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfoo
bar
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/printEscChar.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printEscChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QAn escaped character example is "
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/printInt.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/printInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QAn example integer is 189
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/print/println.wacc Backend", Tag("wacc_examples/valid/IO/print")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/print/println.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QHello World!
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/read/echoBigInt.wacc Backend", Tag("wacc_examples/valid/IO/read")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoBigInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """2147483647
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\Qenter an integer to echo
2147483647
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/read/echoBigNegInt.wacc Backend", Tag("wacc_examples/valid/IO/read")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoBigNegInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """-2147483648
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\Qenter an integer to echo
-2147483648
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/read/echoChar.wacc Backend", Tag("wacc_examples/valid/IO/read")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """K
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\Qenter a character to echo
K
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/read/echoInt.wacc Backend", Tag("wacc_examples/valid/IO/read")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """101
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\Qenter an integer to echo
101
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/read/echoNegInt.wacc Backend", Tag("wacc_examples/valid/IO/read")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoNegInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """-5
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\Qenter an integer to echo
-5
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/read/echoPuncChar.wacc Backend", Tag("wacc_examples/valid/IO/read")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/echoPuncChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """!
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\Qenter a character to echo
!
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/IO/read/read.wacc Backend", Tag("wacc_examples/valid/IO/read")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/IO/read/read.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """350
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\Qinput an integer to continue...
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/array.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/array.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E([0-9a-zA-Z- ]+)\Q = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arrayBasic.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arrayEmpty.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayEmpty.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arrayIndexMayBeArrayIndex.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayIndexMayBeArrayIndex.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q6
7
8
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arrayLength.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayLength.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q4
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arrayLookup.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayLookup.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q43
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arrayNested.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayNested.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q3
3
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arrayOnHeap.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayOnHeap.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
0
1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arrayPrint.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arrayPrint.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E([0-9a-zA-Z- ]+)\Q = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/arraySimple.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/arraySimple.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q42
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/emptyArrayAloneIsFine.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayAloneIsFine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/emptyArrayNextLine.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayNextLine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/emptyArrayPrint.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayPrint.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/emptyArrayReplace.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayReplace.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/emptyArrayScope.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/emptyArrayScope.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/free.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/free.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/lenArrayIndex.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/lenArrayIndex.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q0\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/modifyString.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/modifyString.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qhello world!
Hello world!
Hi!
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/array/printRef.wacc Backend", Tag("wacc_examples/valid/array")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/array/printRef.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QPrinting an array variable gives an address, such as \E([0-9a-zA-Z- ]+)\Q
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/basic/exit/exit-1.wacc Backend", Tag("wacc_examples/valid/basic/exit")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exit-1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/basic/exit/exitBasic.wacc Backend", Tag("wacc_examples/valid/basic/exit")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 7
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/basic/exit/exitBasic2.wacc Backend", Tag("wacc_examples/valid/basic/exit")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitBasic2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 42
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/basic/exit/exitWrap.wacc Backend", Tag("wacc_examples/valid/basic/exit")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/exit/exitWrap.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/basic/skip/comment.wacc Backend", Tag("wacc_examples/valid/basic/skip")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/comment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/basic/skip/commentEoF.wacc Backend", Tag("wacc_examples/valid/basic/skip")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/commentEoF.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/basic/skip/commentInLine.wacc Backend", Tag("wacc_examples/valid/basic/skip")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/commentInLine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/basic/skip/skip.wacc Backend", Tag("wacc_examples/valid/basic/skip")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/basic/skip/skip.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/andExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/andExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
true
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/andOverOrExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/andOverOrExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/boolCalc.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/boolCalc.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/boolExpr1.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/boolExpr1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QCorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/charComparisonExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/charComparisonExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
true
true
true
false
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/divExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/divExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/equalsExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
false
true
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/equalsOverAnd.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverAnd.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
true
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/equalsOverBool.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverBool.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/equalsOverOr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/equalsOverOr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/greaterEqExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/greaterEqExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
true
true
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/greaterExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/greaterExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
true
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/intCalc.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/intCalc.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q72
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/intExpr1.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/intExpr1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QCorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/lessCharExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessCharExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/lessEqExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessEqExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
false
true
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/lessExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/lessExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/longExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 153
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/longExpr2.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 10
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/longExpr3.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longExpr3.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 9
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/longSplitExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longSplitExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 153
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/longSplitExpr2.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/longSplitExpr2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q362880
128
\E"""

      val expExit = 128
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/minusExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q5
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/minusMinusExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusMinusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q3
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/minusNoWhitespaceExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusNoWhitespaceExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/minusPlusExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/minusPlusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/modExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/modExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/multExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/multExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q15
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/multNoWhitespaceExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/multNoWhitespaceExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/negBothDiv.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negBothDiv.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/negBothMod.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negBothMod.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/negDividendDiv.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDividendDiv.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/negDividendMod.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDividendMod.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/negDivisorDiv.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDivisorDiv.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/negDivisorMod.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negDivisorMod.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/negExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/negExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-42
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/notExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/notExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qfalse
true
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/notequalsExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/notequalsExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
true
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/orExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/orExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
true
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/ordAndchrExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/ordAndchrExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qa is 97
99 is c
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/plusExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q35
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/plusMinusExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusMinusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/plusNoWhitespaceExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusNoWhitespaceExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q3
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/plusPlusExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/plusPlusExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q3
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/sequentialCount.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/sequentialCount.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QCan you count to 10?
1
2
3
4
5
6
7
8
9
10
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/expressions/stringEqualsExpr.wacc Backend", Tag("wacc_examples/valid/expressions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/expressions/stringEqualsExpr.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
false
false
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/nested_functions/fibonacciFullRec.wacc Backend", Tag("wacc_examples/valid/function/nested_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fibonacciFullRec.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """30
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\QThis program calculates the nth fibonacci number recursively.
Please enter n (should not be too large): The input n is 30
The nth fibonacci number is 832040
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/nested_functions/fibonacciRecursive.wacc Backend", Tag("wacc_examples/valid/function/nested_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fibonacciRecursive.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QThe first 20 fibonacci numbers are:
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181...
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/nested_functions/fixedPointRealArithmetic.wacc Backend", Tag("wacc_examples/valid/function/nested_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/fixedPointRealArithmetic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QUsing fixed-point real: 10 / 3 * 3 = 10
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/nested_functions/functionConditionalReturn.wacc Backend", Tag("wacc_examples/valid/function/nested_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/functionConditionalReturn.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/nested_functions/mutualRecursion.wacc Backend", Tag("wacc_examples/valid/function/nested_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/mutualRecursion.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qr1: sending 8
r2: received 8
r1: sending 7
r2: received 7
r1: sending 6
r2: received 6
r1: sending 5
r2: received 5
r1: sending 4
r2: received 4
r1: sending 3
r2: received 3
r1: sending 2
r2: received 2
r1: sending 1
r2: received 1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/nested_functions/printInputTriangle.wacc Backend", Tag("wacc_examples/valid/function/nested_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/printInputTriangle.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """13
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\QPlease enter the size of the triangle to print:
-------------
------------
-----------
----------
---------
--------
-------
------
-----
----
---
--
-
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/nested_functions/printTriangle.wacc Backend", Tag("wacc_examples/valid/function/nested_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/printTriangle.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q--------
-------
------
-----
----
---
--
-
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/nested_functions/simpleRecursion.wacc Backend", Tag("wacc_examples/valid/function/nested_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/nested_functions/simpleRecursion.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/argScopeCanBeShadowed.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/argScopeCanBeShadowed.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/asciiTable.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/asciiTable.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QAscii character lookup table:
-------------
|   32 =    |
|   33 = !  |
|   34 = "  |
|   35 = #  |
|   36 = $  |
|   37 = %  |
|   38 = &  |
|   39 = '  |
|   40 = (  |
|   41 = )  |
|   42 = *  |
|   43 = +  |
|   44 = ,  |
|   45 = -  |
|   46 = .  |
|   47 = /  |
|   48 = 0  |
|   49 = 1  |
|   50 = 2  |
|   51 = 3  |
|   52 = 4  |
|   53 = 5  |
|   54 = 6  |
|   55 = 7  |
|   56 = 8  |
|   57 = 9  |
|   58 = :  |
|   59 = ;  |
|   60 = <  |
|   61 = =  |
|   62 = >  |
|   63 = ?  |
|   64 = @  |
|   65 = A  |
|   66 = B  |
|   67 = C  |
|   68 = D  |
|   69 = E  |
|   70 = F  |
|   71 = G  |
|   72 = H  |
|   73 = I  |
|   74 = J  |
|   75 = K  |
|   76 = L  |
|   77 = M  |
|   78 = N  |
|   79 = O  |
|   80 = P  |
|   81 = Q  |
|   82 = R  |
|   83 = S  |
|   84 = T  |
|   85 = U  |
|   86 = V  |
|   87 = W  |
|   88 = X  |
|   89 = Y  |
|   90 = Z  |
|   91 = [  |
|   92 = \  |
|   93 = ]  |
|   94 = ^  |
|   95 = _  |
|   96 = `  |
|   97 = a  |
|   98 = b  |
|   99 = c  |
|  100 = d  |
|  101 = e  |
|  102 = f  |
|  103 = g  |
|  104 = h  |
|  105 = i  |
|  106 = j  |
|  107 = k  |
|  108 = l  |
|  109 = m  |
|  110 = n  |
|  111 = o  |
|  112 = p  |
|  113 = q  |
|  114 = r  |
|  115 = s  |
|  116 = t  |
|  117 = u  |
|  118 = v  |
|  119 = w  |
|  120 = x  |
|  121 = y  |
|  122 = z  |
|  123 = {  |
|  124 = |  |
|  125 = }  |
|  126 = ~  |
-------------
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionDeclaration.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionDoubleReturn.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionDoubleReturn.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q3
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionIfReturns.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionIfReturns.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qgo
1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionManyArguments.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionManyArguments.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qa is 42
b is true
c is u
d is hello
e is \E([0-9a-zA-Z- ]+)\Q
f is \E([0-9a-zA-Z- ]+)\Q
answer is g
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionMultiReturns.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionMultiReturns.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionReturnPair.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionReturnPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q10
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionSimple.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionSimple.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q0
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionSimpleLoop.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionSimpleLoop.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q10
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/functionUpdateParameter.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/functionUpdateParameter.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qy is 1
x is 1
x is now 5
y is still 1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/incFunction.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/incFunction.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q1
4
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/manyArgumentsChar.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/manyArgumentsChar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QA
b
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/manyArgumentsInt.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/manyArgumentsInt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q23
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/negFunction.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/negFunction.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
false
true
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/punning.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/punning.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q0
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/sameArgName.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameArgName.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q99
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/sameArgName2.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameArgName2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q99
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/sameNameAsVar.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/sameNameAsVar.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q5
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/function/simple_functions/usesArgumentWhilstMakingArgument.wacc Backend", Tag("wacc_examples/valid/function/simple_functions")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/function/simple_functions/usesArgumentWhilstMakingArgument.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q12
-4
32
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/if1.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/if2.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/if3.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if3.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/if4.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if4.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/if5.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if5.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/if6.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/if6.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/ifBasic.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/ifFalse.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifFalse.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qhere
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/ifTrue.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/ifTrue.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qhere
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/if/whitespace.wacc Backend", Tag("wacc_examples/valid/if")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/if/whitespace.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q1
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/checkRefPair.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/checkRefPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E([0-9a-zA-Z- ]+)\Q
\E([0-9a-zA-Z- ]+)\Q
true
10
10
true
a
a
true
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/createPair.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/createPair02.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair02.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/createPair03.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createPair03.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/createRefPair.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/createRefPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/free.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/free.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/linkedList.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/linkedList.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qlist = {1, 2, 4, 11}
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/nestedPair.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/nestedPairLeftAssign.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPairLeftAssign.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q7
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/nestedPairRightExtract.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/nestedPairRightExtract.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/null.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/null.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q(nil)
(nil)
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/pairarray.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/pairarray.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q3
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/printNull.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printNull.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q(nil)
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/printNullPair.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printNullPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q(nil)
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/printPair.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E([0-9a-zA-Z- ]+)\Q = (10, a)
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/printPairOfNulls.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/printPairOfNulls.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E([0-9a-zA-Z- ]+)\Q = ((nil),(nil))
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/readPair.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/readPair.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """f 16
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\QPlease enter the first element (char): Please enter the second element (int): The first element was f
The second element was 16
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/writeFst.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/writeFst.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q10
42
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/pairs/writeSnd.wacc Backend", Tag("wacc_examples/valid/pairs")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/pairs/writeSnd.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qa
Z
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc Backend", Tag("wacc_examples/valid/runtimeErr/arrayOutOfBounds")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc Backend", Tag("wacc_examples/valid/runtimeErr/arrayOutOfBounds")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc Backend", Tag("wacc_examples/valid/runtimeErr/arrayOutOfBounds")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/divideByZero/divZero.wacc Backend", Tag("wacc_examples/valid/runtimeErr/divideByZero")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/divZero.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/divideByZero/divideByZero.wacc Backend", Tag("wacc_examples/valid/runtimeErr/divideByZero")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/divideByZero.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/divideByZero/modByZero.wacc Backend", Tag("wacc_examples/valid/runtimeErr/divideByZero")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/divideByZero/modByZero.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/integerOverflow/intJustOverflow.wacc Backend", Tag("wacc_examples/valid/runtimeErr/integerOverflow")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intJustOverflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2147483646
2147483647
\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/integerOverflow/intUnderflow.wacc Backend", Tag("wacc_examples/valid/runtimeErr/integerOverflow")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intUnderflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-2147483647
-2147483648
\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/integerOverflow/intWayOverflow.wacc Backend", Tag("wacc_examples/valid/runtimeErr/integerOverflow")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intWayOverflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2000000000
\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/integerOverflow/intmultOverflow.wacc Backend", Tag("wacc_examples/valid/runtimeErr/integerOverflow")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intmultOverflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2147483
2147483000
\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc Backend", Tag("wacc_examples/valid/runtimeErr/integerOverflow")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-2147483648
\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc Backend", Tag("wacc_examples/valid/runtimeErr/integerOverflow")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-2147483648
\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc Backend", Tag("wacc_examples/valid/runtimeErr/integerOverflow")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-20000
\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc Backend", Tag("wacc_examples/valid/runtimeErr/integerOverflow")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q-2000000000
\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/nullDereference/freeNull.wacc Backend", Tag("wacc_examples/valid/runtimeErr/nullDereference")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/freeNull.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/nullDereference/readNull1.wacc Backend", Tag("wacc_examples/valid/runtimeErr/nullDereference")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/readNull1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/nullDereference/readNull2.wacc Backend", Tag("wacc_examples/valid/runtimeErr/nullDereference")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/readNull2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/nullDereference/setNull1.wacc Backend", Tag("wacc_examples/valid/runtimeErr/nullDereference")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/setNull1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/nullDereference/setNull2.wacc Backend", Tag("wacc_examples/valid/runtimeErr/nullDereference")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/setNull2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/nullDereference/useNull1.wacc Backend", Tag("wacc_examples/valid/runtimeErr/nullDereference")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/useNull1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/runtimeErr/nullDereference/useNull2.wacc Backend", Tag("wacc_examples/valid/runtimeErr/nullDereference")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/runtimeErr/nullDereference/useNull2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\Efatal error:(.|\s)*\Q\E"""

      val expExit = 255
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/ifNested1.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/ifNested1.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/ifNested2.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/ifNested2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcorrect
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/indentationNotImportant.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/indentationNotImportant.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/intsAndKeywords.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/intsAndKeywords.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/printAllTypes.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/printAllTypes.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q( [1, 2, 3] , [a, b, c] )
[ \E([0-9a-zA-Z- ]+)\Q = (a, true), \E([0-9a-zA-Z- ]+)\Q = (b, false) ]
1, 2
array, of, strings
true, false, true
xyz
1, 2, 3
this is a string
true
x
5
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/scope.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scope.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/scopeBasic.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/scopeIfRedefine.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeIfRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
12
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/scopeRedefine.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/scopeSimpleRedefine.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeSimpleRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qtrue
12
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/scopeVars.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeVars.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q2
4
2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/scopeWhileNested.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeWhileNested.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcounting... 5
counting... 4
counting... 3
counting... 2
counting... 1
0 Boom!
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/scopeWhileRedefine.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/scopeWhileRedefine.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qcounting... 5
counting... 4
counting... 3
counting... 2
counting... 1
0 Boom!
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/scope/splitScope.wacc Backend", Tag("wacc_examples/valid/scope")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/scope/splitScope.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q3
2
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/sequence/basicSeq.wacc Backend", Tag("wacc_examples/valid/sequence")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/basicSeq.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/sequence/basicSeq2.wacc Backend", Tag("wacc_examples/valid/sequence")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/basicSeq2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/sequence/boolAssignment.wacc Backend", Tag("wacc_examples/valid/sequence")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/boolAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/sequence/charAssignment.wacc Backend", Tag("wacc_examples/valid/sequence")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/charAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/sequence/exitSimple.wacc Backend", Tag("wacc_examples/valid/sequence")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/exitSimple.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 42
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/sequence/intAssignment.wacc Backend", Tag("wacc_examples/valid/sequence")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/intAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 20
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/sequence/intLeadingZeros.wacc Backend", Tag("wacc_examples/valid/sequence")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/intLeadingZeros.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q42
0
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/sequence/stringAssignment.wacc Backend", Tag("wacc_examples/valid/sequence")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/sequence/stringAssignment.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/_VarNames.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/_VarNames.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 19
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/boolDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/boolDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/boolDeclaration2.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/boolDeclaration2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/capCharDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/capCharDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/charDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/charDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/charDeclaration2.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/charDeclaration2.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/emptyStringDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/emptyStringDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/intDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/intDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/longVarNames.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/longVarNames.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 5
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/manyVariables.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/manyVariables.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/negIntDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/negIntDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/puncCharDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/puncCharDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/stringCarriageReturn.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/stringCarriageReturn.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/stringDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/stringDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/variables/zeroIntDeclaration.wacc Backend", Tag("wacc_examples/valid/variables")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/variables/zeroIntDeclaration.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/fibonacciFullIt.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/fibonacciFullIt.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """30
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\QThis program calculates the nth fibonacci number iteratively.
Please enter n (should not be too large): The input n is 30
The nth fibonacci number is 832040
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/fibonacciIterative.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/fibonacciIterative.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QThe first 20 fibonacci numbers are:
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, ...
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/loopCharCondition.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/loopCharCondition.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QChange c
Should print "Change c" once before.
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/loopIntCondition.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/loopIntCondition.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QChange n
Should print "Change n" once before.
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/max.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/max.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qmax value = 17
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/min.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/min.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qmin value = 10
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/rmStyleAdd.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/rmStyleAdd.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qinitial value of x: 3
(+)(+)(+)(+)(+)(+)(+)
final value of x: 10
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/rmStyleAddIO.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/rmStyleAddIO.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val inp = """2 40
"""
      val inpStream = new ByteArrayInputStream(inp.getBytes())
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = (Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #< inpStream) #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!

      val expResult: String = """\QEnter the first number: Enter the second number: Initial value of x: 2
(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)(+)
final value of x: 42
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/whileBasic.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileBasic.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Q\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/whileBoolFlip.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileBoolFlip.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qflip b!
end of loop
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/whileCount.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileCount.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\QCan you count to 10?
1
2
3
4
5
6
7
8
9
10
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

test("wacc_examples/valid/while/whileFalse.wacc Backend", Tag("wacc_examples/valid/while")) {
  val Success(ast) = Parser.code.parseFromFile(new File("wacc_examples/valid/while/whileFalse.wacc")).get
  Semantic.semanticAnalysis(ast) match {
    case ProgramAndTable(_, _) => {
      val assembly: File = Writer.writeTempFile(Generator.generate(ast))
      val executable: File = File.createTempFile("tempexec", "")
      val ourResult = new ByteArrayOutputStream
      val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} ${assembly.getPath()}")
      val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
      val assResult = assembler.!<
      if (assResult != 0) {
        fail("Unsuccessful gcc assembling")
      }
      val execResult = executor.!
      val expResult: String = """\Qend of loop
\E"""

      val expExit = 0
      assert(expExit.equals(execResult))
      assert(Pattern.matches(expResult, ourResult.toString))
    }
    case _ => assert(false)
  }
}

}
