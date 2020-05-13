package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.model.DataResult
import retrofit2.Response

abstract class SafeRequest {

    companion object {
        private const val NULL_ERROR = "null body"
    }

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): DataResult<T> {

        try {

            val response = call.invoke()

            return if (response.isSuccessful) {
                response.body()?.run {
                    DataResult(
                        RequestStatus.SUCCESS,
                        this as T
                    )
                } ?: DataResult(
                    status = RequestStatus.ERROR,
                    errorMessage = NULL_ERROR
                )
            } else {
                DataResult(
                    status = RequestStatus.ERROR,
                    errorMessage = response.code().toString()
                )
            }
        } catch (ex: Exception) {
            return DataResult(
                status = RequestStatus.ERROR,
                errorMessage = ex.message.orEmpty()
            )
        }
    }
}
