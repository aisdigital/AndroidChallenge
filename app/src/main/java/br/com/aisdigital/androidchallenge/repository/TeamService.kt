package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.model.AuthData
import br.com.aisdigital.androidchallenge.model.TeamData
import br.com.aisdigital.androidchallenge.model.UserInfoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface TeamService {

    @GET("posts")
    suspend fun getTeams(): Response<List<TeamData>>

    @POST("auth")
    suspend fun postAuth(
        @Query("user_email") email: String,
        @Query("user_password") password: String
    ): Response<AuthData>

    @GET("login")
    suspend fun getLogin(@Header("Authorization") token: String): Response<UserInfoData>

}