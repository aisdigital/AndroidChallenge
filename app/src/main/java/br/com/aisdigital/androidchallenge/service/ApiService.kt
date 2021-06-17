package br.com.aisdigital.androidchallenge.service

import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.Token
import br.com.aisdigital.androidchallenge.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("teams")
    fun getTeams() : Single<List<Team>>

    @POST("auth")
    fun auth(@Query("user_email ") mail : String, @Query("user_password") password : String) : Single<Token>

    @GET("login")
    fun login( @Header("Authorization") token : String) : Single<User>
}