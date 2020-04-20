package br.com.aisdigital.androidchallenge.framework

import br.com.aisdigital.androidchallenge.core.data.interfaces.TeamRemote

class TeamRetrofitRemote(private val api: AndroidChallengeApi) :
    TeamRemote {

    override suspend fun getAll() = api.getTeams()
}