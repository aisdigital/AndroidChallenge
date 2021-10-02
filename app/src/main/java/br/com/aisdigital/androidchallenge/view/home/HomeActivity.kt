package br.com.aisdigital.androidchallenge.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.aisdigital.androidchallenge.R

class HomeActivity : AppCompatActivity() {

    companion object {
        const val HOME_LOGIN_EXTRAS = "HOME_LOGIN_EXTRAS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}