package com.mohaberabi.pokemon.core.data.network.util

import com.mohaberabi.pokemon.core.domain.model.AppError

sealed class AppResult<out T> {
    data class Success<T>(
        val data: T,
    ) : AppResult<T>()

    data class Error(
        val error: AppError,
    ) : AppResult<Nothing>()
}


inline fun <reified T> AppResult<T>.onSuccess(
    block: (T) -> Unit,
): AppResult<T> {
    if (this is AppResult.Success) {

        block(this.data)
    }
    return this
}

inline fun <reified T, reified R> AppResult<T>.map(
    block: (T) -> R
): AppResult<R> {
    return when (this) {
        is AppResult.Success -> AppResult.Success(block(this.data))
        is AppResult.Error -> AppResult.Error(
            error = error
        )

    }

}

inline fun <reified T> AppResult<T>.onFailure(
    block: (AppError) -> Unit,
): AppResult<T> {
    if (this is AppResult.Error) {
        block(this.error)
    }
    return this
}

