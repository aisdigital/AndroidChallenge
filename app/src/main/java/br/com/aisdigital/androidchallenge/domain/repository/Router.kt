package br.com.aisdigital.androidchallenge.domain.repository

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.view.home.HomeActivity

class Router(private val activity: AppCompatActivity) {

    fun navigateToHome(loginResponse: LoginResponse) {
        val intent = Intent(activity.applicationContext, HomeActivity::class.java)
        Bundle(1).apply {
            putParcelable(HomeActivity.HOME_LOGIN_EXTRAS, loginResponse)
            intent.putExtras(this)
        }
        activity.startActivity(intent)
    }

}