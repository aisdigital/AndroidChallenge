package br.com.aisdigital.androidchallenge.repository.retrofit

import br.com.aisdigital.androidchallenge.repository.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NbaService {

    @GET("/teams")
    fun getTeams(): Call <List<BaseResponse>>

}
