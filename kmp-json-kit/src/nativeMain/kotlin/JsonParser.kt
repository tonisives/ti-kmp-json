import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class JsonParser(val filePath: String, val storage: Storage) {
    fun getJsonValues(): Map<String, JsonElement> {
        val jsonString = storage.readFile(filePath)
        return Json.decodeFromString<JsonObject>(jsonString).toMap()
    }
}

fun JsonObject.getStringValue(key: String): String? {
    return this[key]
        ?.jsonPrimitive
        ?.content
}

fun JsonElement.getStringValue(key: String): String? {
    return (this as JsonObject)[key]
        ?.jsonPrimitive
        ?.content
}

fun Map<String, JsonElement>.getStringValue(key: String): String? {
    return this[key]
        ?.jsonPrimitive
        ?.content
}