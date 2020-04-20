package br.com.aisdigital.androidchallenge.domain.login

import br.com.aisdigital.androidchallenge.domain.error.AuthenticationFail
import br.com.aisdigital.androidchallenge.domain.user.User

class AuthenticateUser(private val authService: AuthService, private val loginValidator: LoginValidator) {

    suspend fun doAuthentication(login: Login): Login {

        val result = authService.authenticate(login)

        return if(loginValidator.isAuthenticated(result)) result else throw AuthenticationFail
    }

    suspend fun login(login: Login): User {
        return authService.login(login)
    }
}