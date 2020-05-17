package br.com.aisdigital.androidchallenge.di

import br.com.aisdigital.androidchallenge.App
import br.com.aisdigital.androidchallenge.MainActivity
import br.com.aisdigital.androidchallenge.di.module.AppModule
import br.com.aisdigital.androidchallenge.di.module.BackendModule
import br.com.aisdigital.androidchallenge.di.module.RemoteModule
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import br.com.aisdigital.androidchallenge.viewmodel.TeamsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        BackendModule::class,
        RemoteModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    fun inject(activity: MainActivity)

    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: TeamsViewModel)
}