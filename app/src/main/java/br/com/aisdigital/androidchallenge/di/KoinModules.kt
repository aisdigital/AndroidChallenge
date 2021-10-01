package br.com.aisdigital.androidchallenge.di

import br.com.aisdigital.androidchallenge.domain.repository.RemoteDataSource
import br.com.aisdigital.androidchallenge.domain.repository.Repository
import br.com.aisdigital.androidchallenge.domain.repository.remote.API
import br.com.aisdigital.androidchallenge.domain.repository.remote.RetrofitClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun loadKoinModules() {
    loadKoinModules(
        listOf(networkModule)
    )
}

private val networkModule = module {
    factory<Repository> {
        RemoteDataSource(api = get())
    }

    single(override = true) {
        RetrofitClient.createService(API::class.java)
    }
}