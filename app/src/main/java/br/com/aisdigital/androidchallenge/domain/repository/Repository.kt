package br.com.aisdigital.androidchallenge.domain.repository

import br.com.aisdigital.androidchallenge.domain.model.auth.AuthResponse
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.domain.model.teams.TeamsResponse

interface Repository {

    suspend fun doAuthenticate(email: String, password: String): AuthResponse
    suspend fun doLogin(token: String): LoginResponse
    suspend fun getTeams(): List<TeamsResponse>

}