package br.com.aisdigital.androidchallenge.domain.datasource

import br.com.aisdigital.androidchallenge.domain.model.AuthToken
import br.com.aisdigital.androidchallenge.domain.model.Team
import br.com.aisdigital.androidchallenge.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AndroidChallengeService {

    @GET("teams")
    suspend fun getTeams(): Response<List<Team>>

    @POST("auth")
    suspend fun getAuthToken(
        @Query("user_email") email: String,
        @Query("user_password") password: String
    ): Response<AuthToken>

    @GET("login")
    suspend fun getUser(@Header("x-auth-token") token: String): Response<User>

}