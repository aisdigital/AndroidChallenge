package br.com.aisdigital.androidchallenge.di

import br.com.aisdigital.androidchallenge.domain.repository.AuthInteractor
import br.com.aisdigital.androidchallenge.domain.repository.LoginInteractor
import br.com.aisdigital.androidchallenge.domain.repository.RemoteDataSource
import br.com.aisdigital.androidchallenge.domain.repository.Repository
import br.com.aisdigital.androidchallenge.domain.repository.remote.API
import br.com.aisdigital.androidchallenge.domain.repository.remote.RetrofitClient
import br.com.aisdigital.androidchallenge.viewmodel.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun loadKoinModules() {
    loadKoinModules(
        listOf(interactorModule, networkModule, viewModelModule)
    )
}

private val interactorModule = module {
    factory {
        AuthInteractor(
            repository = get(),
            resources = androidContext().resources
        )
    }
    factory {
        LoginInteractor(
            repository = get(),
            resources = androidContext().resources
        )
    }
}

private val viewModelModule = module {
    viewModel { (authInteractor: AuthInteractor, loginInteractor: LoginInteractor) ->
        LoginViewModel(
            authInteractor = authInteractor,
            loginInteractor = loginInteractor,
            resources = androidContext().resources
        )
    }
}

private val networkModule = module {
    factory<Repository> {
        RemoteDataSource(api = get())
    }

    single(override = true) {
        RetrofitClient.createService(API::class.java)
    }
}