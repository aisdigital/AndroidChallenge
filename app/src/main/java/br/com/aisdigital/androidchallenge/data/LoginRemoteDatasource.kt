package br.com.aisdigital.androidchallenge.data

import android.util.Log
import br.com.aisdigital.androidchallenge.data.api.LoginApi
import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRemoteDatasource(
    private val retrofitClient: RetrofitClient,
    private val loginApi: LoginApi = retrofitClient.retrofit.create(LoginApi::class.java)
) : ILoginRemoteDatasource {

    override suspend fun authenticate(
        email: String,
        password: String
    ): ResultApi<AuthenticationResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = loginApi.authenticate(email, password)
                ResultApi.Success(response)
            } catch (e: Exception) {
                Log.e(this.javaClass.name, e.message ?: e.localizedMessage ?: "")
                ResultApi.Error(e.message)
            }
        }

    override suspend fun login(authToken: String) =
        withContext(Dispatchers.IO) {
            try {
                val response = loginApi.login(authToken)
                ResultApi.Success(response)
            } catch (e: Exception) {
                Log.e(this.javaClass.name, e.message ?: e.localizedMessage ?: "")
                ResultApi.Error(e.message)
            }
        }
}