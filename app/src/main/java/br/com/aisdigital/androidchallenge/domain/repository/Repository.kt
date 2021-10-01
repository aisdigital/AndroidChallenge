package br.com.aisdigital.androidchallenge.domain.repository

interface Repository {

    suspend fun doAuthenticate()
    suspend fun doLogin()
    suspend fun getTeams()

}