package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.dto.Team
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsRepository {

    private val service = RetrofitConfig.remoteServices

    fun getTeams() {
        val call = service.teams()

        call.enqueue(object : Callback<ArrayList<Team>> {
            override fun onResponse(
                call: Call<ArrayList<Team>>,
                response: Response<ArrayList<Team>>
            ) {

            }

            override fun onFailure(call: Call<ArrayList<Team>>, t: Throwable) {

            }
        })
    }
}