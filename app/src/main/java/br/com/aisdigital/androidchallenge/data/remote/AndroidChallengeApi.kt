package br.com.aisdigital.androidchallenge.data.remote

import retrofit2.Response
import retrofit2.http.*

interface AndroidChallengeApi {

    @POST("auth")
    suspend fun getToken(
        @Query("user_email") user_email: String,
        @Query("user_password") user_password: String
    ): Response<Token>

    @GET("login")
    suspend fun login(@Header("x-auth-token") token: String): Response<UserRequest>

    @GET("teams")
    suspend fun getTeams(): List<TeamRequest>
}