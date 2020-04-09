package br.com.aisdigital.androidchallenge.domain.datasource

import br.com.aisdigital.androidchallenge.domain.model.AuthToken
import br.com.aisdigital.androidchallenge.domain.model.Team
import br.com.aisdigital.androidchallenge.domain.model.User

interface AndroidChallengeContract {

    suspend fun getTeams(): List<Team>

    suspend fun getAuthToken(email: String, password: String): AuthToken

    suspend fun getUser(authToken: String): User

}