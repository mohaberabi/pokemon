package com.mohaberabi.pokemon.core.data.network.util

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type


class DeferredApiResponseCallAdapter<T>(
    private val scope: CoroutineScope,
    private val returnType: Type
) : CallAdapter<T, Deferred<AppResult<T>>> {
    override fun adapt(call: Call<T>): Deferred<AppResult<T>> {
        val deferred = CompletableDeferred<AppResult<T>>().apply {
            invokeOnCompletion {
                if (isCancelled && !call.isCanceled) {
                    call.cancel()
                }
            }
        }

        scope.launch {
            val callResponse = call.awaitResponse()
            val result = callResponse.safeApiCall()
            deferred.complete(result)
        }


//        scope.launch {
//            try {
//                val callResponse = call.awaitResponse()
//                if (callResponse.code() in successCodeRange) {
//                    val doneResponse = AppResult.Success(
//                        data = callResponse.body() ?: Unit as T,
//                    )
//                    deferred.complete(doneResponse)
//                } else {
//                    val apiResponse = AppResult.Error(callResponse, callResponse.code())
//                    deferred.complete(apiResponse)
//                }
//
//            } catch (e: Exception) {
//                if (e is CancellationException) throw e
//            }
//        }
        return deferred
    }

    override fun responseType(): Type = returnType
}