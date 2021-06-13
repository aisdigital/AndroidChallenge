package br.com.aisdigital.androidchallenge.data.repository

import br.com.aisdigital.androidchallenge.data.UserPreferences
import br.com.aisdigital.androidchallenge.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun auth(
        email: String,
        password: String
    ) = safeApiCall {
        api.auth(email, password)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }
}