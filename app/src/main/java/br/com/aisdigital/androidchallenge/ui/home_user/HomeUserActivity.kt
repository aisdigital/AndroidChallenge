package br.com.aisdigital.androidchallenge.ui.home_user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.ui.auth.AuthActivity
import br.com.aisdigital.androidchallenge.ui.startNewActivity


class HomeUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_user)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startNewActivity(AuthActivity::class.java)
        finish()
    }
}