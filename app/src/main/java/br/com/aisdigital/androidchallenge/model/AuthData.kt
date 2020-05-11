package br.com.aisdigital.androidchallenge.model

import com.squareup.moshi.Json

data class AuthData(
    @Json(name = "token") val token: String
)