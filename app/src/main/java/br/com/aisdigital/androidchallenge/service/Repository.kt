package br.com.aisdigital.androidchallenge.service

class Repository constructor(private val retrofitService: RetrofitService) {

    fun getAllTeams() = retrofitService.getAllTeams()
}