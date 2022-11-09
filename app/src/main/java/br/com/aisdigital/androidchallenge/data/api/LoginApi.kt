package br.com.aisdigital.androidchallenge.data.api

import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {
    @POST("auth")
    suspend fun authenticate(
        @Query("user_email") email: String,
        @Query("user_password") password: String,
    ): AuthenticationResponse

    @GET("login")
    suspend fun login(
        @Header("x-auth-token") authToken: String
    ): LoginResponse
}