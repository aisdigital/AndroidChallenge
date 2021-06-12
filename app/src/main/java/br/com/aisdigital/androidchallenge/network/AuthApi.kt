package br.com.aisdigital.androidchallenge.network

import br.com.aisdigital.androidchallenge.responses.AuthResponse
import br.com.aisdigital.androidchallenge.responses.LoginResponse
import br.com.aisdigital.androidchallenge.responses.TeamsResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/auth")
    suspend fun auth(
        @Field("user_email") email: String,
        @Field("user_password") password: String
    ): AuthResponse

    @GET("/login")
    suspend fun login(
        @Field("x-auth-token") token: String
    ): LoginResponse

    @POST("/teams")
    suspend fun listTeams(): List<TeamsResponse>
}