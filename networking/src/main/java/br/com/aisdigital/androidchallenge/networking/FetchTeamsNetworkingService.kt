package br.com.aisdigital.androidchallenge.networking

import br.com.aisdigital.androidchallenge.domain.teams.FetchTeamService
import br.com.aisdigital.androidchallenge.domain.teams.Team

class FetchTeamsNetworkingService(private val restApi: TeamsIO) : FetchTeamService {

    override suspend fun fetchAll(): List<Team> {
        return restApi.listTeams().asTeamEntityList()
    }
}