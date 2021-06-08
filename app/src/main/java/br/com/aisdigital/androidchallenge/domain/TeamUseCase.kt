package br.com.aisdigital.androidchallenge.domain

import br.com.aisdigital.androidchallenge.repository.RepositoryImpl

class TeamUseCase() {
    private val repository: RepositoryImpl

    init {
        repository = RepositoryImpl()
    }

    fun getTeamList(): List<TeamModel>{
        val finalTeamList = mutableListOf<TeamModel>()
        val teamList = repository.getTeamsList()

        teamList?.forEach {
                val teamView = TeamModel(it.name,it.city,it.conference,it.teamImageUrl,it.description)
                finalTeamList.add(teamView)
        }

        return finalTeamList
    }

}