package wacc

import java.io.File
import java.io.PrintWriter

// Converts a list of instructions into an assembly file
object Writer{
    def write(ir: IR, filename: String): Unit = {
        val file = new File(filename + ".s")
        val writer = new PrintWriter(file)
        writer.write(AssemblyWriter.irToAssembly(ir))
        writer.close()
    }

    def writeTempFile(ir: IR): File = {
        val file = File.createTempFile("tempass", ".s")
        val writer = new PrintWriter(file)
        writer.write(AssemblyWriter.irToAssembly(ir))
        writer.close()
        file
    }
}
