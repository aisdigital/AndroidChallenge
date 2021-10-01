package br.com.aisdigital.androidchallenge.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.di.loadKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@LoginActivity)
            loadKoinModules()
        }

        setContentView(R.layout.activity_login)
    }
}
