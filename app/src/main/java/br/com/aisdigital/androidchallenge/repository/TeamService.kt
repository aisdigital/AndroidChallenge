package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.model.Auth
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.UserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface TeamService {

    @GET("teams")
    suspend fun getTeams(): Response<List<Team>>

    @POST("auth")
    suspend fun postAuth(
        @Query("user_email") email: String,
        @Query("user_password") password: String
    ): Response<Auth>

    @GET("login")
    suspend fun getLogin(@Header("Authorization") token: String): Response<UserInfo>
}
