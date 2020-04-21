package br.com.aisdigital.androidchallenge.networking

import br.com.aisdigital.androidchallenge.domain.login.AuthService
import br.com.aisdigital.androidchallenge.domain.login.Login
import br.com.aisdigital.androidchallenge.domain.user.User

class FetchUserNetworkingService(private val restApi: UserIO) : AuthService {

    override suspend fun authenticate(login: Login): Login {

        val response = restApi.auth(login.email, login.password)

        if(response.token.isNotEmpty()) {
            return login.copy(token = response.token)
        }

        return login
    }

    override suspend fun login(login: Login): User {
        return restApi.login(login.token).asUserEntity()
    }
}