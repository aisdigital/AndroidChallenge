package br.com.aisdigital.androidchallenge

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import br.com.aisdigital.androidchallenge.di.AppComponent
import br.com.aisdigital.androidchallenge.di.DaggerAppComponent
import br.com.aisdigital.androidchallenge.di.module.AppModule
import br.com.aisdigital.androidchallenge.di.module.RemoteModule

open class App : MultiDexApplication(), Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {}

    override fun onActivityStarted(activity: Activity?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity?) {}

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}

    open val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .remoteModule(RemoteModule())
            .build()

    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}

