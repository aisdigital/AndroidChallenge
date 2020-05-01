package br.com.aisdigital.androidchallenge.data

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

import br.com.aisdigital.androidchallenge.domain.login.LoginDataSource
import br.com.aisdigital.androidchallenge.domain.user.Gender
import br.com.aisdigital.androidchallenge.domain.user.User

class CachedLogin(private val context: Context) : LoginDataSource {

    companion object {
        private const val USER_LOGGED_IN = "user_logged_in"
        private const val USER_NAME = "user_name"
        private const val USER_AGE = "user_age"
        private const val USER_GENDER = "user_gender"
    }

    private val defaultSharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun isUserLoggedIn(): Boolean {
        return defaultSharedPreferences.getBoolean(USER_LOGGED_IN, false)
    }

    override fun getUser(): User? {
        return if(isUserLoggedIn()) {
            val gender = defaultSharedPreferences.getString(USER_GENDER, "")
            User(
                defaultSharedPreferences.getString(USER_NAME, "") ?: "",
                defaultSharedPreferences.getInt(USER_AGE, 0),
                if(gender == Gender.MALE.name) Gender.MALE else Gender.FEMALE
            )
        } else {
            null
        }
    }

    override fun save(user: User) {
        val editor = defaultSharedPreferences.edit()
        editor.putBoolean(USER_LOGGED_IN, true)
        editor.putString("name", user.name)
        editor.putInt("age", user.age)
        editor.putString("gender", user.gender.name)
        editor.apply()
    }

    override fun clear() {
        defaultSharedPreferences.edit()
        .clear()
        .apply()
    }
}