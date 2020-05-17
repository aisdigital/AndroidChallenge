package br.com.aisdigital.androidchallenge.di.module

import br.com.aisdigital.androidchallenge.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import org.jetbrains.anko.defaultSharedPreferences

@Module
open class AppModule (private val app: App) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    open fun provideContext() = app.applicationContext!!

    @Provides
    @Singleton
    open fun provideSharedPreferences() = app.defaultSharedPreferences

    @Provides
    @Singleton
    open fun provideResources() = app.resources!!
}