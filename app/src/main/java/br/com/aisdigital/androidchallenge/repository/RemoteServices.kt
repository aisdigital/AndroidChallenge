package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.dto.Team
import br.com.aisdigital.androidchallenge.dto.Token
import br.com.aisdigital.androidchallenge.dto.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RemoteServices {

    @POST("/auth")
    fun auth(): Call<Token>

    @GET("/login")
    fun login(
        @Header("Authorization") token: String
    ): Call<User>

    @GET("/teams")
    fun teams(): Call<ArrayList<Team>>
}