import co.touchlab.kermit.Kermit
import kotlinx.cinterop.toKString
import kotlinx.serialization.json.JsonElement
import platform.posix.getenv

val kermit = Kermit()

class KmpJsonKit {
    val homePath: String by lazy {
        getenv("HOME")?.toKString() ?: "no home path"
    }

    val testFilePath: String by lazy {
        "$homePath/testFile.json"
    }

    val storage = Storage()
    val jsonParser = JsonParser(testFilePath, storage)

    init {
        kermit.d { "KmpJson initialized" }
        storage.writeFile(testFilePath, jsonToWrite)
    }

    fun destroy() {

    }

    fun getJsonValues(): Map<String, JsonElement> {
        return jsonParser.getJsonValues()
    }
}

val jsonToWrite = """
    {
        "firstKey" : "firstValue",
        "secondKey" : "secondValue",
        "thirdKey" : "thirdValue",
        "fourthKey" : "fourthValue",
        "fifthKey" : "fifthValue"
    }
""".trimIndent()