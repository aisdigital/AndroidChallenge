package br.com.aisdigital.androidchallenge.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("age") val age: String?,
    @SerializedName("gender") val gender: String?
)
