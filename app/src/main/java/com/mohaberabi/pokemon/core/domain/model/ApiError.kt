package com.mohaberabi.pokemon.core.domain.model

sealed interface ApiError : AppError {
    data class Network(val type: ErrorType, val message: String? = null) : ApiError {
        enum class ErrorType {
            REQUEST_TIMEOUT,
            TOO_MANY_REQUESTS,
            NO_INTERNET,
            CONNECTION_TIMEOUT,
            PAYLOAD_TOO_LARGE,
            UNAUTHORIZED,
            FORBIDDEN,
            NOT_FOUND,
            SERVER_ERROR,
            SERIALIZATION_ERROR,
            UNKNOWN
        }
    }
}

data class ErrorResponse(
    val error: ErrorDetail
)

data class ErrorDetail(
    val message: Any?
)
