package br.com.aisdigital.androidchallenge.module

import br.com.aisdigital.androidchallenge.di.module.BackendModule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import kotlinx.coroutines.runBlocking
import br.com.aisdigital.androidchallenge.extensions.readJSON
import br.com.aisdigital.androidchallenge.remote.BackendService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
open class BackendModuleTest : BackendModule() {

    @Singleton
    override fun providesBackendService(retrofit: Retrofit) = mock<BackendService>{
        // postAuth(any, any)
        on {
            runBlocking { postAuth(any(), any()) }
        } doReturn readJSON("auth")

        // getLogin()
        on {
            runBlocking { getLogin() }
        } doReturn readJSON("login")

        // getStatements(any)
        on {
            runBlocking { getTeams() }
        } doReturn readJSON("teams")
    }

}