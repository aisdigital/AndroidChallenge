package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.Token
import br.com.aisdigital.androidchallenge.model.User
import br.com.aisdigital.androidchallenge.remote.BackendService
import javax.inject.Inject

class BackendRepository @Inject constructor(
    private val backendService: BackendService
) {

    suspend fun postAuthAsync(user: String, password: String): Token {
        return backendService.postAuth(user, password)
    }

    suspend fun getLoginAsync(): User {
        return backendService.getLogin()
    }

    suspend fun getTeamsAsync(): List<Team> {
        return backendService.getTeams()
    }
}
