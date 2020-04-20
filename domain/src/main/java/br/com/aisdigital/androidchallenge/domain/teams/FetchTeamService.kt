package br.com.aisdigital.androidchallenge.domain.teams

interface FetchTeamService {

    suspend fun fetchAll() : List<Team>
}