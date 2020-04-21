package br.com.aisdigital.androidchallenge.networking

import retrofit2.http.GET

interface TeamsIO {

    @GET("/teams")
    suspend fun listTeams() : RawTeams
}