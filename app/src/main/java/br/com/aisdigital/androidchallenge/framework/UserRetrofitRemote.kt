package br.com.aisdigital.androidchallenge.framework

import br.com.aisdigital.androidchallenge.core.data.interfaces.UserRemote

class UserRetrofitRemote(private val api: AndroidChallengeApi) :
    UserRemote {

    override suspend fun getAuthToken(email: String, password: String) =
        api.getAuthToken(email, password)

    override suspend fun login(token: String) = api.login(token)
}