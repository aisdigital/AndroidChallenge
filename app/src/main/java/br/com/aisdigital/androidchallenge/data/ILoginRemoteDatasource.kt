package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.LoginResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi

interface ILoginRemoteDatasource {
    suspend fun authenticate(email: String, password: String) : ResultApi<AuthenticationResponse>
    suspend fun login(authToken: String) : ResultApi<LoginResponse>
}