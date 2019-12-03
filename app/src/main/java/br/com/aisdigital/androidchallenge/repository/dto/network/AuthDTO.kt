package br.com.aisdigital.androidchallenge.repository.dto.network

import com.google.gson.annotations.SerializedName

data class AuthDTO(

    @SerializedName("user_email")
    var email: String? = null,

    @SerializedName("user_password")
    var password: String? = null

)