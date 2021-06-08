package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.repository.model.BaseResponse
import br.com.aisdigital.androidchallenge.repository.retrofit.NbaAPI
import br.com.aisdigital.androidchallenge.repository.retrofit.NbaService

class RepositoryImpl {

    private val service: NbaService

    init {
        val apiBuilder = NbaAPI()
        val retrofit = apiBuilder.createRetrofit()
        service = apiBuilder.createNbaAPI(retrofit)
    }

    fun getTeamsList(): List<BaseResponse>? {
        val response = service.getTeams().execute()

        if (response.isSuccessful) {
            val team = response.body()

            if (team != null) {
                return team
            }
        }
        return null
    }
}
