package br.com.aisdigital.androidchallenge

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.login.LoginActivity
import br.com.aisdigital.androidchallenge.login.LoginViewModel
import br.com.aisdigital.androidchallenge.teams.TeamsListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    lateinit var clazz: Class<out Activity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed ({
            val intent = Intent().apply {

                clazz = if(loginViewModel.hasUserLoggedIn()) {
                    TeamsListActivity::class.java
                } else {
                    LoginActivity::class.java
                }

                setClass(this@SplashActivity, clazz)
            }

            startActivity(intent)
            finish()
        }, 2000)
    }
}
