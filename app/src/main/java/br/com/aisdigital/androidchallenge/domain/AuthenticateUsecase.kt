package br.com.aisdigital.androidchallenge.domain

import br.com.aisdigital.androidchallenge.data.LoginRepository
import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi

class AuthenticateUsecase(val loginRepository: LoginRepository) {
    suspend fun authenticate(email: String, password: String) : ResultApi<AuthenticationResponse> {
        return loginRepository.authenticate(email, password)
    }
}