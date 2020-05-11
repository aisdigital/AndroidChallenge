package br.com.aisdigital.androidchallenge.model

import com.squareup.moshi.Json

data class UserInfoData (
    @Json(name = "name") val name: String,
    @Json(name = "age") val age: String,
    @Json(name = "gender") val gender: Int
)