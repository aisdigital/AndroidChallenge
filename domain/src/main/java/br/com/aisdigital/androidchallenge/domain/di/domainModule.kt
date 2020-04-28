package br.com.aisdigital.androidchallenge.domain.di

import br.com.aisdigital.androidchallenge.domain.login.AuthenticateUser
import br.com.aisdigital.androidchallenge.domain.login.LoginValidator
import br.com.aisdigital.androidchallenge.domain.teams.FetchTeams
import org.koin.dsl.module

val domain = module {
    factory {
        FetchTeams(api = get())
    }

    factory {
        AuthenticateUser(authService = get(), loginValidator = get())
    }

    factory {
        LoginValidator()
    }
}

val domainModule = listOf(domain)