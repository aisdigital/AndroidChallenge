package br.com.aisdigital.androidchallenge.domain.model.error

import android.content.res.Resources
import br.com.aisdigital.androidchallenge.R
import com.google.gson.Gson
import retrofit2.HttpException

class ErrorHandler(private val resources: Resources, private val gson: Gson = Gson()) {

    companion object {
        private const val UNEXPECTED_ERROR_NAME = "NO-NAME"
    }

    fun buildErrorResponse(e: Exception? = null) = if (e is HttpException) {
        parseToError(e.response()?.errorBody()?.string())
    } else {
        buildGenericErrorResponse()
    }

    private fun parseToError(backendErrorString: String?): ErrorResponse {
        return try {
            return gson.fromJson(backendErrorString.orEmpty(), ErrorResponse::class.java)
        } catch (ex: Exception) {
            buildGenericErrorResponse()
        }
    }

    private fun buildGenericErrorResponse(): ErrorResponse {
        return ErrorResponse(
            ErrorBody(
                UNEXPECTED_ERROR_NAME,
                resources.getString(R.string.generic_error_message),
                null,
            )
        )
    }

}