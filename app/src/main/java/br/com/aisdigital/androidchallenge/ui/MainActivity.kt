package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.UserPreferences
import br.com.aisdigital.androidchallenge.ui.auth.AuthActivity
import br.com.aisdigital.androidchallenge.ui.home_user.HomeUserActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
            //Toast.makeText(this, it ?: "Token is null", Toast.LENGTH_SHORT).show()
            val activity =
                if (it == null) AuthActivity::class.java else HomeUserActivity::class.java
            startNewActivity(activity)
        })
    }
}
