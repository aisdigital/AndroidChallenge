package br.com.aisdigital.androidchallenge.data.team

import br.com.aisdigital.androidchallenge.model.Team
import io.reactivex.Single
import retrofit2.http.GET

interface TeamApi {

    @GET("/teams")
    fun getAll(): Single<List<Team>>
}