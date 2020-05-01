package br.com.aisdigital.androidchallenge.di

import br.com.aisdigital.androidchallenge.login.LoginViewModel
import br.com.aisdigital.androidchallenge.teams.TeamsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentation = module {
    viewModel {
        TeamsViewModel(
            useCase = get(),
            ioDispatcher = Dispatchers.IO
        )
    }

    viewModel {
        LoginViewModel(
            loginValidator = get(),
            usecase = get(),
            dataSource = get(),
            ioDispatcher = Dispatchers.IO
        )
    }
}

val presentationModule = listOf(presentation)