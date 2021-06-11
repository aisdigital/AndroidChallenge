package br.com.aisdigital.androidchallenge.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NbaAPI {

    private val baseurl = "https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io"

    fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun createNbaAPI(retrofit: Retrofit): NbaService =
        retrofit.create((NbaService::class.java))
}