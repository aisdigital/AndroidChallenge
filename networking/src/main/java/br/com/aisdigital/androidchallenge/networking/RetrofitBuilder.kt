package br.com.aisdigital.androidchallenge.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitBuilder {

    operator fun invoke(baseUrl: String, httpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(Json.nonstrict.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
    }

    const val BASE_URL = "https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io"
}