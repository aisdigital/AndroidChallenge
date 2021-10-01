package br.com.aisdigital.androidchallenge.domain.repository.remote

import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @POST("auth")
    suspend fun doAuthenticate()

    @GET("login")
    suspend fun doLogin()

    @GET("teams")
    suspend fun getTeams()

}