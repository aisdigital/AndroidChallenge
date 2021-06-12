package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.network.AuthApi

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun auth(
        email: String,
        password: String
    ) = safeApiCall {
        api.auth(email, password)
    }

    suspend fun login(
        token: String
    ) = safeApiCall {
        api.login(token)
    }
}