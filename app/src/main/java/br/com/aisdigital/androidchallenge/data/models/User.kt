package br.com.aisdigital.androidchallenge.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") var name: String,
    @SerializedName("age") var age: String,
    @SerializedName("gender") var gender: String
)