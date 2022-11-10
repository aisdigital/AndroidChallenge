package br.com.aisdigital.androidchallenge.data.mock

import br.com.aisdigital.androidchallenge.data.ILoginRemoteDatasource
import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.LoginResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi

class MockRemoteDatasource(var success: Boolean = false) : ILoginRemoteDatasource {

    override suspend fun authenticate(
        email: String,
        password: String
    ): ResultApi<AuthenticationResponse> {
        return if (success) {
            ResultApi.Success(AuthenticationResponse("12345678"))
        } else {
            ResultApi.Error("error")
        }
    }

    override suspend fun login(authToken: String): ResultApi<LoginResponse> {
        return if (success) {
            ResultApi.Success(LoginResponse("Carlos", age = "31", gender = "Male"))
        } else {
            ResultApi.Error("error")
        }
    }

}