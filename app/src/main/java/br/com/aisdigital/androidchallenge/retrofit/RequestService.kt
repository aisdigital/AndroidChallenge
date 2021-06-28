package br.com.aisdigital.androidchallenge.retrofit

import br.com.aisdigital.androidchallenge.model.AuthResponse
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RequestService {

    @POST("/auth")
    fun postAuth(
        @Query("user_email") userEmail: String,
        @Query("user_password") password: String
    ): Call<AuthResponse>

    @GET("/login")
    fun getLogin(): Call<User>

    @GET("/teams")
    fun getTeams(): Call<List<Team>>
}