package br.com.aisdigital.androidchallenge

import android.app.Application
import br.com.aisdigital.androidchallenge.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidChallengeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@AndroidChallengeApp)
            modules(allModules)
        }
    }
}