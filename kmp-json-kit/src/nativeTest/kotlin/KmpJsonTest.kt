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

    class StorageMock : Storage() {
        override fun readFile(absolutePath: String): String {
            return testJson
        }
    }
}

val testJson = """
    {
        "testOneKey" : "testOneValue",
        "testTwoKey" : "testTwoValue",
        "testThreeKey" : "testThreeValue"
    }
""".trimIndent()