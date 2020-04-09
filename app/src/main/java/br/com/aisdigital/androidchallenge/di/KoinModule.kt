package br.com.aisdigital.androidchallenge.di

import br.com.aisdigital.androidchallenge.adapter.TeamsAdapter
import br.com.aisdigital.androidchallenge.domain.AndroidChallengeApi
import br.com.aisdigital.androidchallenge.domain.model.Team
import br.com.aisdigital.androidchallenge.domain.repository.AndroidChallengeRepository
import br.com.aisdigital.androidchallenge.viewmodel.TeamListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val serviceModule = module {
    single {
        AndroidChallengeApi.getApiService()
    }
}

private val repositoryModule = module {
    single {
        AndroidChallengeRepository(service = get())
    }
}

private val viewModelModule = module {
    viewModel { TeamListViewModel(repository = get()) }
//    viewModel { (postId: Int) -> PostDetailViewModel(repository = get(), postId = postId) }
}

private val adapterModule = module {
    factory { (action: (Team) -> Unit) ->
        TeamsAdapter(
            itemClickAction = action
        )
    }
}

fun loadModules() {
    loadKoinModules(
        listOf(
            serviceModule,
            repositoryModule,
            viewModelModule,
            adapterModule
        )
    )
}