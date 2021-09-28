package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.model.Team
import retrofit2.Call
import retrofit2.http.GET

interface InterfaceApi {
    @GET("teams")
    fun ApiGetReposResult(): Call<List<Team>>

}


