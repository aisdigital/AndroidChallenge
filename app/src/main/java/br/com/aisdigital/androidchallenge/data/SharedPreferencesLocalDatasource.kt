package br.com.aisdigital.androidchallenge.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesLocalDatasource(val applicationContext: Context) : ISharedPreferencesLocalDatasource {

    private val sharedPreferencesName = "AndroidChallengePrefs"
    private val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }
}