package br.com.aisdigital.androidchallenge.data


interface ISharedPreferencesLocalDatasource {
    fun saveString(key: String, value: String)
    fun getString(key: String) : String
}