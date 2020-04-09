package br.com.aisdigital.androidchallenge.domain

import retrofit2.Response
import java.io.IOException

abstract class SafeRequest {

    companion object {
        private const val NULL_ERROR = "null body"
    }

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            response.body()?.run {
                return this
            } ?: run {
                throw IOException(NULL_ERROR)
            }
        } else {
            throw IOException(response.code().toString())
        }
    }
}