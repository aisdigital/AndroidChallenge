package br.com.aisdigital.androidchallenge.data.di

import br.com.aisdigital.androidchallenge.data.CachedLogin
import br.com.aisdigital.androidchallenge.domain.login.LoginDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {
    single <LoginDataSource>{
        CachedLogin(androidContext())
    }
}

val dataModule = listOf(module)