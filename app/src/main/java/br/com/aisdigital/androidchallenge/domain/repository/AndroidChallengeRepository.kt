package br.com.aisdigital.androidchallenge.domain.repository

import br.com.aisdigital.androidchallenge.domain.SafeRequest
import br.com.aisdigital.androidchallenge.domain.datasource.AndroidChallengeContract
import br.com.aisdigital.androidchallenge.domain.datasource.AndroidChallengeService
import br.com.aisdigital.androidchallenge.domain.model.AuthToken
import br.com.aisdigital.androidchallenge.domain.model.Team
import br.com.aisdigital.androidchallenge.domain.model.User

class AndroidChallengeRepository(private val service: AndroidChallengeService) :
    AndroidChallengeContract,
    SafeRequest() {

    override suspend fun getTeams(): List<Team> = apiRequest {
        service.getTeams()
    }

    override suspend fun getAuthToken(email: String, password: String): AuthToken = apiRequest {
        service.getAuthToken(email, password)
    }

    override suspend fun getUser(authToken: String): User = apiRequest {
        service.getUser(authToken)
    }
}
