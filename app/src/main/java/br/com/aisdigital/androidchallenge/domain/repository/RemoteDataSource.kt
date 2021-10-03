package br.com.aisdigital.androidchallenge.domain.repository

import br.com.aisdigital.androidchallenge.domain.model.auth.AuthResponse
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.domain.model.teams.TeamsResponse
import br.com.aisdigital.androidchallenge.domain.repository.remote.API

class RemoteDataSource(private val api: API) : Repository {

    override suspend fun doAuthenticate(email: String, password: String): AuthResponse {
        return api.doAuthenticate(email, password)
    }

    override suspend fun doLogin(token: String): LoginResponse {
        return api.doLogin(token)
    }

    override suspend fun getTeams(): List<TeamsResponse> {
        return api.getTeams()
    }

}