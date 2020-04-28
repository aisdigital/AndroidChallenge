package br.com.aisdigital.androidchallenge.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    operator fun invoke(baseUrl: String, httpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    const val BASE_URL = "https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io"
}