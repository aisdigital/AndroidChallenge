package br.com.aisdigital.androidchallenge.domain.repository

import br.com.aisdigital.androidchallenge.domain.repository.remote.API

class RemoteDataSource(private val api: API): Repository {

    override suspend fun doAuthenticate() {
        api.doAuthenticate()
    }

    override suspend fun doLogin() {
        api.doLogin()
    }

    override suspend fun getTeams() {
        api.getTeams()
    }

}