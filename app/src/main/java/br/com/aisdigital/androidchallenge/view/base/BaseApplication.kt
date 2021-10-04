package br.com.aisdigital.androidchallenge.view.base

import androidx.multidex.MultiDexApplication
import br.com.aisdigital.androidchallenge.di.loadKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            loadKoinModules()
        }
    }

}