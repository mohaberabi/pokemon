//package com.mohaberabi.pokemon.core.data.network.util
//
//import com.google.gson.*
//import java.lang.reflect.ParameterizedType
//import java.lang.reflect.Type
//
//class ApiResponseAdapter<T> : JsonDeserializer<AppResult<T>>, JsonSerializer<AppResult<T>> {
//
//    override fun deserialize(
//        json: JsonElement,
//        typeOfT: Type,
//        context: JsonDeserializationContext
//    ): AppResult<T> {
//        val jsonObject = json.asJsonObject
//        return when (val type = jsonObject.get("type").asString) {
//            "Success" -> {
//                val data = context.deserialize<T>(
//                    jsonObject.get("data"),
//                    (typeOfT as ParameterizedType).actualTypeArguments[0]
//                )
//                val statusCode = jsonObject.get("statusCode").asInt
//                AppResult.Success(data, statusCode)
//            }
//
//            "Error" -> {
//                val payload = jsonObject.get("payload")
//                val statusCode = jsonObject.get("statusCode").asInt
//                AppResult.Error(payload, statusCode)
//            }
//
//            "Exception" -> {
//                val throwableMessage = jsonObject.get("throwable")?.asString
//                AppResult.Exception(Exception(throwableMessage))
//            }
//
//            else -> throw JsonParseException("Unknown type: $type")
//        }
//    }
//
//    override fun serialize(
//        src: AppResult<T>,
//        typeOfSrc: Type,
//        context: JsonSerializationContext
//    ): JsonElement {
//        val jsonObject = JsonObject()
//        when (src) {
//            is AppResult.Success -> {
//                jsonObject.addProperty("type", "Success")
//                jsonObject.add("data", context.serialize(src.data))
//                jsonObject.addProperty("statusCode", src.statusCode)
//            }
//
//            is AppResult.Error -> {
//                jsonObject.addProperty("type", "Error")
//                jsonObject.add("payload", context.serialize(src.payload))
//                jsonObject.addProperty("statusCode", src.statusCode)
//            }
//
//            is AppResult.Exception -> {
//                jsonObject.addProperty("type", "Exception")
//                jsonObject.addProperty("throwable", src.throwable.message)
//            }
//        }
//        return jsonObject
//    }
//}