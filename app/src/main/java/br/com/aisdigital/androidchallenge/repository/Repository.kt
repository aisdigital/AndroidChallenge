package br.com.aisdigital.androidchallenge.repository

import ApiClient
import br.com.aisdigital.androidchallenge.model.TeamModel
import br.com.aisdigital.androidchallenge.model.User
import com.example.myapplication.model.Token

class Repository(var apiClient: ApiClient) {

    suspend fun getTeamsRepository(): MutableList<TeamModel> {
        return apiClient.getTeams()
    }
    suspend fun  getUserRepository(token:String): User {
        return apiClient.getUser()
    }
    suspend fun  getTokenRepository(): Token {
        return apiClient.getToken()
    }



}