package br.com.aisdigital.androidchallenge.remote

import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.Token
import br.com.aisdigital.androidchallenge.model.User
import retrofit2.http.*

interface BackendService {

    @FormUrlEncoded
    @POST("auth")
    suspend fun postAuth(
        @Field("user_email") user: String,
        @Field("user_password") password: String
    ): Token

    @GET("login")
    suspend fun getLogin(): User

    @GET("teams")
    suspend fun getTeams(): List<Team>
}