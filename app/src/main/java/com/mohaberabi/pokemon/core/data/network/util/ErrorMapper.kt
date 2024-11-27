package com.mohaberabi.pokemon.core.data.network.util

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.mohaberabi.pokemon.core.domain.model.ApiError
import com.mohaberabi.pokemon.core.domain.model.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


private fun parseErrorBody(errorBody: ResponseBody): String {
    return try {
        val gson = Gson()
        val errorResponse = gson.fromJson(String(errorBody.bytes()), ErrorResponse::class.java)
        errorResponse.error.message?.let {
            if (it is String) it
            else (it as? List<*>)?.joinToString("\n") { item -> item.toString() } ?: "Unknown error"
        } ?: "Unknown error"
    } catch (e: Exception) {
        "Error parsing error response"
    }
}

suspend fun <T> Response<T>?.safeApiCall(
): AppResult<T> {
    return try {
        val response = this ?: return AppResult.Error(
            ApiError.Network(
                ApiError.Network.ErrorType.SERVER_ERROR,

                )
        )

        val body = response.body()
        val errorBody = response.errorBody()
        if (response.isSuccessful && body != null) {
            AppResult.Success(body)
        } else if (errorBody != null) {
            val errorResponse = parseErrorBody(errorBody)
            AppResult.Error(
                ApiError.Network(
                    ApiError.Network.ErrorType.SERVER_ERROR,
                    errorResponse
                )
            )
        } else {
            AppResult.Error(
                ApiError.Network(
                    ApiError.Network.ErrorType.UNKNOWN,
                    "Unknown API error"
                )
            )
        }
    } catch (e: IOException) {
        AppResult.Error(
            ApiError.Network(
                ApiError.Network.ErrorType.NO_INTERNET,
                "No Internet Connection"
            )
        )
    } catch (e: SocketTimeoutException) {
        AppResult.Error(
            ApiError.Network(
                ApiError.Network.ErrorType.CONNECTION_TIMEOUT,
                "Connection Timeout"
            )
        )
    } catch (e: HttpException) {
        val errorType = when (e.code()) {
            401 -> ApiError.Network.ErrorType.UNAUTHORIZED
            403 -> ApiError.Network.ErrorType.FORBIDDEN
            404 -> ApiError.Network.ErrorType.NOT_FOUND
            408 -> ApiError.Network.ErrorType.REQUEST_TIMEOUT
            413 -> ApiError.Network.ErrorType.PAYLOAD_TOO_LARGE
            429 -> ApiError.Network.ErrorType.TOO_MANY_REQUESTS
            in 500..599 -> ApiError.Network.ErrorType.SERVER_ERROR
            else -> ApiError.Network.ErrorType.UNKNOWN
        }
        AppResult.Error(ApiError.Network(errorType, e.message()))
    } catch (e: JsonParseException) {
        AppResult.Error(
            ApiError.Network(
                ApiError.Network.ErrorType.SERIALIZATION_ERROR,
                "Response Parsing Error"
            )
        )
    } catch (e: Exception) {
        AppResult.Error(
            ApiError.Network(
                ApiError.Network.ErrorType.UNKNOWN,
                e.localizedMessage ?: "Unknown Error"
            )
        )
    }
}