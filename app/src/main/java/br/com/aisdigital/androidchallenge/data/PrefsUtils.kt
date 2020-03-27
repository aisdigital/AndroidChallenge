package br.com.aisdigital.androidchallenge.data

import android.content.Context

class PrefsUtils(val context: Context) {

    fun write(key: String, value: String): Boolean {
        val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return with(prefs.edit()) {
            putString(key, value)
            commit()
        }
    }

    fun read(key: String): String? {
        val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return prefs.getString(key, "")
    }

    fun exists(key: String): Boolean {
        val prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return prefs.contains(key)
    }
}