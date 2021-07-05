import kotlinx.cinterop.*
import platform.posix.*

/**
 * Reading/writing to files
 *
 */
class Storage : IStorage {
    override fun readFile(absolutePath: String): String {
        val returnBuffer = StringBuilder()

        val file = fopen(absolutePath, "r")
            ?: throw IllegalArgumentException("Cannot open input file $absolutePath")

        try {
            memScoped {
                val readBufferLength = 64 * 1024
                val buffer = allocArray<ByteVar>(readBufferLength)
                var line = fgets(buffer, readBufferLength, file)?.toKString()
                while (line != null) {
                    returnBuffer.append(line)
                    line = fgets(buffer, readBufferLength, file)?.toKString()
                }
            }
        } finally {
            fclose(file)
        }

        return returnBuffer.toString()
    }

    override fun writeFile(absolutePath: String, text: String) {
        val path = absolutePath

        val file = fopen(path, "w")
            ?: throw IllegalArgumentException("Cannot open output file $path")
        try {
            memScoped {
                if (fputs(text, file) == EOF) throw Error("File write error")
            }
        } finally {
            fclose(file)
        }
    }
}

interface IStorage {
    fun readFile(absolutePath: String): String;
    fun writeFile(absolutePath: String, text: String);
}