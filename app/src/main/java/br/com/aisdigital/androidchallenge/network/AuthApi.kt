package br.com.aisdigital.androidchallenge.network

import br.com.aisdigital.androidchallenge.responses.AuthResponse
import br.com.aisdigital.androidchallenge.responses.LoginResponse
import br.com.aisdigital.androidchallenge.responses.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("/auth")
    suspend fun auth(
        @Query("user_email") email: String,
        @Query("user_password") password: String
    ): AuthResponse

    @GET("/login")
    suspend fun login(
        @Header("x-auth-token") token: String
    ): LoginResponse

    @GET("/teams")
    suspend fun listTeams(): List<TeamsResponse>
}