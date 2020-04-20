package br.com.aisdigital.androidchallenge.domain.teams

import br.com.aisdigital.androidchallenge.domain.error.NoTeamsFound

class FetchTeams (private val api: FetchTeamService) {

    suspend fun listAll(): List<Team> {
        val response = api.fetchAll()

        return if(response.isNotEmpty()) response else throw NoTeamsFound
    }
}