package org.example.composemphelloworld.networking.util

sealed interface Error

typealias Failed = Error

sealed interface Result<out D, out E: Failed> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E: Failed>(val error: E) : Result<Nothing, E>
}

inline fun <T, E: Failed> Result<T, E>.onSuccess(action: (T) -> Unit) : Result<T, E> {
    return when(this) {
        is Result.Success -> {
            action(data)
            this
        }
        is Result.Error -> this
    }
}

inline fun <T, E: Failed> Result<T, E>.onError(action: (E) -> Unit) : Result<T, E> {
    return when(this) {
        is Result.Success -> this
        is Result.Error -> {
            action(error)
            this
        }
    }
}