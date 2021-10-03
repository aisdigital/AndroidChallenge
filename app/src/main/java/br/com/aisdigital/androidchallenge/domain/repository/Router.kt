package br.com.aisdigital.androidchallenge.domain.repository

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.view.home.HomeActivity

class Router(private val activity: Application) {

    fun navigateToHome(loginResponse: LoginResponse) {
        val intent = Intent(activity.applicationContext, HomeActivity::class.java)
            .addFlags(FLAG_ACTIVITY_NEW_TASK)
        Bundle(1).apply {
            putParcelable(HomeActivity.HOME_LOGIN_EXTRAS, loginResponse)
            intent.putExtras(this)
        }
        activity.startActivity(intent)
    }

}