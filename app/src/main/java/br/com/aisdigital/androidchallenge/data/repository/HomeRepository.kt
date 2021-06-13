package br.com.aisdigital.androidchallenge.data.repository

import br.com.aisdigital.androidchallenge.data.network.AuthApi

class HomeRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(
        token: String
    ) = safeApiCall {
        api.login(token)
    }

}