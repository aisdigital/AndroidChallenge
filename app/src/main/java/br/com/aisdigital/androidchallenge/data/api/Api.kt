package br.com.aisdigital.androidchallenge.data.api

import br.com.aisdigital.androidchallenge.data.model.Login
import br.com.aisdigital.androidchallenge.data.model.User
import br.com.aisdigital.androidchallenge.data.model.Team
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("auth")
    fun auth(@Query("user_email ") email: String, @Query("user_password ") password : String): Observable<Login>

    @GET("login")
    fun getUser(): Observable<User>

    @GET("teams")
    fun getTeams(): Observable<Team>
}