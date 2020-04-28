package br.com.aisdigital.androidchallenge.networking

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UserIO {

    @POST("/auth")
    suspend fun auth(@Query("user_email") login: String, @Query("user_password") password: String): RawAuth

    @GET("/login")
    suspend fun login(@Header("x-auth-token") token: String): RawUser
}