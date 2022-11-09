package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.api.LoginApi
import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRemoteDatasource(
    private val baseRemoteDatasource: BaseRemoteDatasource,
    private val loginApi: LoginApi = baseRemoteDatasource.retrofit.create(LoginApi::class.java)
) : BaseRemoteDatasource(), ILoginRemoteDatasource {

    override suspend fun authenticate(
        email: String,
        password: String
    ): ResultApi<AuthenticationResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = loginApi.authenticate(email, password)
                ResultApi.Success(response)
            } catch (e: Exception) {
                ResultApi.Error(e.message)
            }
        }

    override suspend fun login(authToken: String) =
        withContext(Dispatchers.IO) {
            try {
                val response = loginApi.login(authToken)
                ResultApi.Success(response)
            } catch (e: Exception) {
                ResultApi.Error(e.message)
            }
        }
}