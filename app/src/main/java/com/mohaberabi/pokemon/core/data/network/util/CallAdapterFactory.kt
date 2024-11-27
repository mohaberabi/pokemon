package com.mohaberabi.pokemon.core.data.network.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class ApiResponseCallAdapterFactory(
    private val scope: CoroutineScope
) : CallAdapter.Factory() {


    companion object {
        @JvmStatic
        fun create(scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())) =
            ApiResponseCallAdapterFactory(scope)
    }

    override fun get(
        returnType: Type,
        p1: Array<out Annotation>,
        p2: Retrofit
    ): CallAdapter<*, *>? {
        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(callType)
        if (rawType != AppResult::class.java) {
            return null
        }
        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return DeferredApiResponseCallAdapter<Any>(scope, resultType)
//        when (getRawType(returnType)) {
//            Call::class.java -> {
//                val callType = getParameterUpperBound(0, returnType as ParameterizedType)
//                val rawType = getRawType(callType)
//                if (rawType != AppResult::class.java) {
//                    return null
//                }
//                val resultType = getParameterUpperBound(0, callType as ParameterizedType)
//                return null
//            }
//
//            Deferred::class.java -> {
//                val callType = getParameterUpperBound(0, returnType as ParameterizedType)
//                val rawType = getRawType(callType)
//                if (rawType != AppResult::class.java) {
//                    return null
//                }
//
//                val resultType = getParameterUpperBound(0, callType as ParameterizedType)
//                return DeferredApiResponseCallAdapter<Any>(scope, resultType)
//            }
//
//            else -> return null
//        }
    }
}


