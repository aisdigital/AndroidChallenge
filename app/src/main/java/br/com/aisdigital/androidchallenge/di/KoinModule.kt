package br.com.aisdigital.androidchallenge.di

import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.UserInfo
import br.com.aisdigital.androidchallenge.repository.TeamApi
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import br.com.aisdigital.androidchallenge.viewmodel.DetailViewModel
import br.com.aisdigital.androidchallenge.viewmodel.HomeViewModel
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import br.com.aisdigital.androidchallenge.viewmodel.ProfileViewModel
import org.koin.android.ext.koin.androidContext
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
    viewModel { (router: AppRouter) ->
        LoginViewModel(
            repository = get(),
            router = router,
            resources = androidContext().resources
        )
    }
    viewModel { HomeViewModel(repository = get()) }
    viewModel { (userInfo: UserInfo) ->
        ProfileViewModel(
            resources = androidContext().resources,
            userInfo = userInfo
        )
    }
    viewModel { (team: Team) ->
        DetailViewModel(
            resources = androidContext().resources,
            team = team
        )
    }
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
