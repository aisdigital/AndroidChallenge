package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.model.LoginResponse

interface ISharedPreferencesLocalDatasource {
    fun clearLocalData()

    fun saveLoginData(key: String, value: LoginResponse)
    fun getLoginData(key: String) : LoginResponse?

    fun saveString(key: String, value: String)
    fun getString(key: String) : String
}