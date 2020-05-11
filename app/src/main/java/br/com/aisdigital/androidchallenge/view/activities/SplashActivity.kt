package br.com.aisdigital.androidchallenge.view.activities

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.fadeTransition
import br.com.aisdigital.androidchallenge.internal.AppRouter

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_TIME = 2000L
    }

    private val router = AppRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        setupRunning()
    }

    private fun setupRunning() {
        Handler()
            .postDelayed({
                router.goToLogin()
                finish()

                fadeTransition()
            }, SPLASH_TIME)
    }
}
