
package wacc

import org.scalatest.matchers.should.Matchers
import java.io.File
import java.io.ByteArrayOutputStream
import org.scalatest.funsuite.AnyFunSuite
import scala.sys.process.{Process}
import org.scalatest.Outcome


class IntegrationTest extends AnyFunSuite with Matchers{

  override protected def withFixture(test: NoArgTest): Outcome = {
    val maker = Process("make")
    maker.!
    super.withFixture(test)
  }

  test("Integration test print") {
    val executable: File = File.createTempFile("tempexec", "")
    val ourResult = new ByteArrayOutputStream
    val compiler = Process(s"./compile wacc_examples/valid/IO/print/print.wacc")
    compiler.! 
    val assembler = Process(s"arm-linux-gnueabi-gcc -mcpu=arm1176jzf-s -mtune=arm1176jzf-s -x assembler -o ${executable.getPath()} print.s")
    val executor = Process(s"qemu-arm -L /usr/arm-linux-gnueabi/ ${executable.getPath()}") #> ourResult
    val assResult = assembler.!<
    if (assResult != 0) {
      fail("Unsuccessful gcc assembling")
    }
    val execResult = executor.!
    val expResult: String = """Hello World!"""

    val expExit = 0
    assert(expExit.equals(execResult))
    assert(expResult.equals(ourResult.toString))
  }

}
