package br.com.aisdigital.androidchallenge.data.network

import br.com.aisdigital.androidchallenge.data.responses.AuthResponse
import br.com.aisdigital.androidchallenge.data.responses.LoginResponse
import br.com.aisdigital.androidchallenge.data.responses.TeamListResponse
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
    suspend fun getTeamList(): List<TeamListResponse>
}