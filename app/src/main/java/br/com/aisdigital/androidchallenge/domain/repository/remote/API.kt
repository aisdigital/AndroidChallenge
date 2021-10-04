package br.com.aisdigital.androidchallenge.domain.repository.remote

import br.com.aisdigital.androidchallenge.domain.model.auth.AuthResponse
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.domain.model.teams.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface API {

    @POST("auth")
    suspend fun doAuthenticate(
        @Header("user_email") email: String,
        @Header("user_password") password: String
    ): AuthResponse

    @GET("login")
    suspend fun doLogin(
        @Header("x-auth-token") token: String
    ): LoginResponse

    @GET("teams")
    suspend fun getTeams(): List<TeamsResponse>

}