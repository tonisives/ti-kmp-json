import kotlin.test.Test

class KmpJsonTest {
    @Test
    fun returnsMapOfJsonObjects() {
        // use fake storage, assert its json values are returned from JsonParser
        val storage = StorageMock()
        val jsonParser = JsonParser("any", storage)

        val jsonMap = jsonParser.getJsonValues()

        assert(jsonMap.keys.size == 3)
        assert(jsonMap.getStringValue("testTwoKey") == "testTwoValue")
    }

    class StorageMock : IStorage {
        override fun readFile(absolutePath: String): String {
            return testJson
        }

        override fun writeFile(absolutePath: String, text: String) {
            // nothing
        }
    }
}

class StorageMock : IStorage {
    override fun readFile(absolutePath: String): String {
        return testJson
    }

    override fun writeFile(absolutePath: String, text: String) {
        // nothing
    }
}

val testJson = """
    {
        "testOneKey" : "testOneValue",
        "testTwoKey" : "testTwoValue",
        "testThreeKey" : "testThreeValue"
    }
""".trimIndent()