package br.com.aisdigital.androidchallenge.repository.retrofit

import br.com.aisdigital.androidchallenge.repository.model.TeamResponse
import br.com.aisdigital.androidchallenge.repository.model.LoginRequest
import br.com.aisdigital.androidchallenge.repository.model.User
import retrofit2.Call
import retrofit2.http.*

interface NbaService {

    @GET("/teams")
    fun getTeams(): Call<List<TeamResponse>>

    @POST("/auth")
    fun login(
        @Query("user_email") user_email: String,
        @Query("user_password") user_password: String
    ): Call<LoginRequest>

    @GET("/login")
    fun autentication(
        @Header("x-auth-token") token: String
    ): Call<User>


    // fazer um request no primeiro usando post

    // get com tokem que voltar


}
