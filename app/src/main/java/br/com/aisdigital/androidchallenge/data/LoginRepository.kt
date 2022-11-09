package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.LoginResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi

class LoginRepository(val loginRemoteDatasource: ILoginRemoteDatasource) {

    suspend fun authenticate(email: String, password: String) : ResultApi<AuthenticationResponse> {
        //TODO se sucesso salvar em shared pref
        return loginRemoteDatasource.authenticate(email, password)
    }

    suspend fun login() : ResultApi<LoginResponse> {
        //TODO pegar token do shared pref
        val authToken = "seaes"
        return loginRemoteDatasource.login(authToken)
    }
}