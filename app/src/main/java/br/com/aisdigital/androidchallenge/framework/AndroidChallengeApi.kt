package br.com.aisdigital.androidchallenge.framework

import br.com.aisdigital.androidchallenge.core.domain.model.Authorization
import br.com.aisdigital.androidchallenge.core.domain.model.Team
import br.com.aisdigital.androidchallenge.core.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AndroidChallengeApi {

    /**
     *  Get all teams from remote source.
     */
    @GET("teams")
    suspend fun getTeams(): List<Team>

    /**
     *  Get authorization token.
     */
    @POST("auth")
    suspend fun getAuthToken(
        @Query("user_email") email: String,
        @Query("user_password") password: String
    ): Authorization

    /**
     *  Get user profile from remote source.
     */
    @GET("login")
    suspend fun login(@Header("x-auth-token") token: String): User
}