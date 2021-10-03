package br.com.aisdigital.androidchallenge.di

import androidx.appcompat.app.AppCompatActivity
import br.com.aisdigital.androidchallenge.domain.model.error.ErrorHandler
import br.com.aisdigital.androidchallenge.domain.repository.*
import br.com.aisdigital.androidchallenge.domain.repository.interactor.AuthInteractor
import br.com.aisdigital.androidchallenge.domain.repository.interactor.LoginInteractor
import br.com.aisdigital.androidchallenge.domain.repository.interactor.TeamsInteractor
import br.com.aisdigital.androidchallenge.domain.repository.remote.API
import br.com.aisdigital.androidchallenge.domain.repository.remote.RetrofitClient
import br.com.aisdigital.androidchallenge.viewmodel.home.HomeViewModel
import br.com.aisdigital.androidchallenge.viewmodel.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun loadKoinModules() {
    loadKoinModules(
        listOf(
            interactorModule,
            viewModelModule,
            routerModule,
            networkModule,
            errorHandlerModule,
            resourcesModule
        )
    )
}

private val interactorModule = module {
    factory {
        AuthInteractor(
            repository = get(),
            errorHandler = get()
        )
    }
    factory {
        LoginInteractor(
            repository = get(),
            errorHandler = get()
        )
    }
    factory {
        TeamsInteractor(
            repository = get(),
            errorHandler = get()
        )
    }
}

private val viewModelModule = module {
    viewModel { (router: Router) ->
        LoginViewModel(
            router = router,
            authInteractor = get(),
            loginInteractor = get(),
            resources = get()
        )
    }
    viewModel {
        HomeViewModel(
            teamsInteractor = get()
        )
    }
}

private val routerModule = module {
    factory { (activity: AppCompatActivity) ->
        Router(activity)
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

private val errorHandlerModule = module(override = true) {
    single { ErrorHandler(get()) }
}

private val resourcesModule = module(override = true) {
    single { androidContext().resources }
}