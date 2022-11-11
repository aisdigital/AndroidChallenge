package br.com.aisdigital.androidchallenge.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.util.Log.ERROR
import br.com.aisdigital.androidchallenge.data.model.LoginResponse
import com.google.gson.GsonBuilder

class SharedPreferencesLocalDatasource(applicationContext: Context) : ISharedPreferencesLocalDatasource {

    private val sharedPreferencesName = "AndroidChallengePrefs"
    private val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
    private val gson = GsonBuilder().create()

    override fun clearLocalData() {
        sharedPreferences.edit().clear().apply()
    }

    override fun saveLoginData(key: String, value: LoginResponse) {
        val jsonString = gson.toJson(value)
        sharedPreferences.edit().putString(key, jsonString).apply()
    }

    override fun getLoginData(key: String): LoginResponse? {
        val value = sharedPreferences.getString(key, null)
        return try {
            GsonBuilder().create().fromJson(value, LoginResponse::class.java)
        } catch (e: Exception) {
            Log.e(this.javaClass.name, e.message ?: e.localizedMessage ?: "")
            null
        }
    }

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }
}