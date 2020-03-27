package br.com.aisdigital.androidchallenge.data.login

import br.com.aisdigital.androidchallenge.model.AuthResponse
import br.com.aisdigital.androidchallenge.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    @POST("/auth")
    fun authenticate(
        @Query("user_email") email: String,
        @Query("user_password ") password: String): Single<AuthResponse>


    @GET("/login")
    fun login(@Header("x-auth-token") token: String): Single<LoginResponse>
}