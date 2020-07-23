package br.com.aisdigital.androidchallenge.data.models

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token") var token: String
)