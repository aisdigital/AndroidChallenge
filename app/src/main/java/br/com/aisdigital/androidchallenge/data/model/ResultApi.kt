package br.com.aisdigital.androidchallenge.data.model

sealed class ResultApi<T> {
    data class Success<T>(val data: T) : ResultApi<T>()
    data class Error<T>(val errorMessage: String?) : ResultApi<T>()
}
