package br.com.aisdigital.androidchallenge.domain.model.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error") val error: ErrorBody
)

data class ErrorBody(
    @SerializedName("name") val name: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("header") val header: String?
)