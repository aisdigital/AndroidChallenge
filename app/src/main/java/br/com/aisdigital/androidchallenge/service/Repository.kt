package br.com.aisdigital.androidchallenge.service

import br.com.aisdigital.androidchallenge.service.retrofit.RetrofitService

class Repository constructor(private val retrofitService: RetrofitService) {

    fun getAllTeams() = retrofitService.getAllTeams()

    fun postAuthentication(email: String, password: String) =
        retrofitService.postAuthentication(email, password)

    fun getLogin(token: String) = retrofitService.getLogin(token)
}