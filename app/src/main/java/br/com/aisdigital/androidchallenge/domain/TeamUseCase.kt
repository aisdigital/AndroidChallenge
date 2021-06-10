package br.com.aisdigital.androidchallenge.domain

import br.com.aisdigital.androidchallenge.domain.model.TeamModel
import br.com.aisdigital.androidchallenge.repository.RepositoryImpl

class TeamUseCase {
    private val repository: RepositoryImpl = RepositoryImpl()

    var onListReceived: (List<TeamModel>) -> Unit = {}

    fun getTeamList() {
        repository.apply {

            onTeamListReceived = {
                val teamList = mutableListOf<TeamModel>()
                it?.forEach {
                    teamList.add(
                        TeamModel(
                            it.name,
                            it.city,
                            it.conference,
                            it.teamImageUrl,
                            it.description
                        )
                    )
                }
                onListReceived(teamList)
            }
            getTeamsList()
        }
    }
}