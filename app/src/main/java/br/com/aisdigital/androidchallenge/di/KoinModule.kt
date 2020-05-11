package br.com.aisdigital.androidchallenge.di

import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.repository.TeamApi
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
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

private val viewModelModule = module {
    viewModel { (router: AppRouter) -> LoginViewModel(repository = get(), router = router) }
}

fun loadAppModules() {
    loadKoinModules(
        listOf(
            serviceModule,
            repositoryModule,
            viewModelModule
        )
    )
}