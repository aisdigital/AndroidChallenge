package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.LoginResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi
import org.koin.java.KoinJavaComponent.inject

class LoginRepository(private val loginRemoteDatasource: ILoginRemoteDatasource, private val sharedPreferencesLocalDatasource: ISharedPreferencesLocalDatasource) {

    private val authTokenSharedPrefKey = "authentication_token"
    private val loginDataSharedPrefKey = "login_data"

    suspend fun authenticate(email: String, password: String) : ResultApi<AuthenticationResponse> {
        return when(val result = loginRemoteDatasource.authenticate(email, password)) {
            is ResultApi.Success -> {
                sharedPreferencesLocalDatasource.saveString(authTokenSharedPrefKey, result.data.token ?: "")
                result
            }
            is ResultApi.Error -> {
                result
            }
        }
    }

    suspend fun login() : ResultApi<LoginResponse> {
        val authToken = sharedPreferencesLocalDatasource.getString(authTokenSharedPrefKey)
        return when (val result = loginRemoteDatasource.login(authToken)) {
            is ResultApi.Success -> {
                sharedPreferencesLocalDatasource.saveLoginData(loginDataSharedPrefKey, result.data)
                result
            }
            is ResultApi.Error -> {
                result
            }
        }
    }

    fun clearLocalData() {
        sharedPreferencesLocalDatasource.clearLocalData()
    }
}