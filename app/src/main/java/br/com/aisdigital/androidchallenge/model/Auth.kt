package br.com.aisdigital.androidchallenge.model

import com.squareup.moshi.Json

data class Auth(
    @Json(name = "token") val token: String
)
