package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.domain.model.TeamModel
import br.com.aisdigital.androidchallenge.repository.model.TeamResponse
import br.com.aisdigital.androidchallenge.repository.retrofit.NbaAPI
import br.com.aisdigital.androidchallenge.repository.retrofit.NbaService

class RepositoryImpl {

    private val service: NbaService
    var onTokenReceived: (token: String) -> Unit = {}
    var onTeamListReceived: (list: List<TeamResponse>?) -> Unit = {}

    init {
        val apiBuilder = NbaAPI()
        val retrofit = apiBuilder.createRetrofit()
        service = apiBuilder.createNbaAPI(retrofit)
    }

    fun getTeamsList() {
        val response = service.getTeams().execute()

        if (response.isSuccessful) {
            val teamList = response.body()

            if (teamList != null) {

                onTeamListReceived(teamList)
            }
        }

    }

    fun login(email: String, senha: String) {

        val response = service.login(email, senha).execute()

        if (response.isSuccessful) {
            val loginRequest = response.body()

            if (loginRequest != null) {
                onTokenReceived(loginRequest.token)
            }
        }
    }
}
