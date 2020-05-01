package br.com.aisdigital.androidchallenge

import android.app.Application
import br.com.aisdigital.androidchallenge.data.di.dataModule
import br.com.aisdigital.androidchallenge.di.presentationModule
import br.com.aisdigital.androidchallenge.domain.di.domainModule
import br.com.aisdigital.androidchallenge.networking.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)

            modules(domainModule + dataModule + networkingModule + presentationModule)
        }
    }
}