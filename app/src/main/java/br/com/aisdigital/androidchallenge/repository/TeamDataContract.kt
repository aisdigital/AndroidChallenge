package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.model.Auth
import br.com.aisdigital.androidchallenge.model.DataResult
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.UserInfo

interface TeamDataContract {

    suspend fun getTeams(): DataResult<List<Team>>

    suspend fun postAuth(
        email: String,
        password: String
    ): DataResult<Auth>

    suspend fun getLogin(token: String): DataResult<UserInfo>
}
