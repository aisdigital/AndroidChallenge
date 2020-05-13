package br.com.aisdigital.androidchallenge

import android.app.Application
import br.com.aisdigital.androidchallenge.di.loadAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //TODO: Remover para rodar os testes. Não identifiquei o problema ainda
        setupKoin()
    }

    fun setupKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            loadAppModules()
        }
    }
}
