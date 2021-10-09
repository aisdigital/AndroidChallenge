package br.com.aisdigital.androidchallenge.service

import Team
import br.com.aisdigital.androidchallenge.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.END_POINT_TEAMS)
    fun getAllTeams(): Call<List<Team>>

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