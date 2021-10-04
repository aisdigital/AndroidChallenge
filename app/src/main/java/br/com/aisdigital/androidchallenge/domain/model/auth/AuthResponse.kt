package br.com.aisdigital.androidchallenge.domain.model.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(@SerializedName("token") val token: String)
