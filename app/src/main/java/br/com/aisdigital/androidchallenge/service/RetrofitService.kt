package br.com.aisdigital.androidchallenge.service

import TeamResponse
import UserResponse
import br.com.aisdigital.androidchallenge.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {
    @GET(Constants.END_POINT_TEAMS)
    fun getAllTeams(): Call<List<TeamResponse>>

    @POST(Constants.END_POINT_AUTH)
    fun postAuthentication(
        @Query("user_email") userEmail: String,
        @Query("user_password") userPassword: String
    ): Call<UserResponse>


    @GET(Constants.END_POINT_LOGIN)
    fun getLogin(@Header("x-auth-token") token: String): Call<UserResponse>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}