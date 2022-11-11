package br.com.aisdigital.androidchallenge.domain

import br.com.aisdigital.androidchallenge.data.LoginRepository
import br.com.aisdigital.androidchallenge.data.model.LoginResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi

class LoginUsecase(private val loginRepository: LoginRepository) {
    suspend fun login() : ResultApi<LoginResponse> {
        return loginRepository.login()
    }
}