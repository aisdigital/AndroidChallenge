package br.com.aisdigital.androidchallenge.data.mock

import br.com.aisdigital.androidchallenge.data.ISharedPreferencesLocalDatasource
import br.com.aisdigital.androidchallenge.data.model.LoginResponse

class MockLocalDatasource : ISharedPreferencesLocalDatasource {
    var string: String? = null
    var loginData: LoginResponse? = null

    override fun clearLocalData() {
        string = null
        loginData = null
    }

    override fun saveLoginData(key: String, value: LoginResponse) {
        loginData = value
    }

    override fun getLoginData(key: String): LoginResponse {
       return loginData!!
    }

    override fun saveString(key: String, value: String) {
        string = value
    }

    override fun getString(key: String): String {
        return string!!;
    }

}