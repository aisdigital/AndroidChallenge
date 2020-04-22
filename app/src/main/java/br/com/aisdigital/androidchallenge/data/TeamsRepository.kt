package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.model.Team
import io.reactivex.Observable

class TeamsRepository(private val dataSource: TeamsDataSource) {

    fun getTeams(): Observable<List<Team>> {
        return dataSource.getTeams().map {
            it
        }
    }
}
