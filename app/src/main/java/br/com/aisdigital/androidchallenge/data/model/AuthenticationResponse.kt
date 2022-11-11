package br.com.aisdigital.androidchallenge.data.model

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("token") val token: String?
)
