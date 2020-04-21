package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.api.RetrofitClient
import br.com.aisdigital.androidchallenge.data.model.Team
import io.reactivex.Observable

class TeamsDataSource {
    private val client = RetrofitClient.getClient()

    fun getTeams(): Observable<Team> {
        return client.getTeams().map { it }
    }
}

