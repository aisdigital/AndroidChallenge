package br.com.aisdigital.androidchallenge.domain.login

data class Login (
    val email: String,
    val password: String,
    val token: String = ""
)