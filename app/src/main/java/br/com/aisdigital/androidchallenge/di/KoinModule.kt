package br.com.aisdigital.androidchallenge.di

import br.com.aisdigital.androidchallenge.repository.TeamApi
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val serviceModule = module {
    single {
        TeamApi.getApiService()
    }
}

private val repositoryModule = module {
    single {
        TeamRepository(service = get())
    }
}


fun loadAppModules() {
    loadKoinModules(
        listOf(
            serviceModule,
            repositoryModule
        )
    )
}