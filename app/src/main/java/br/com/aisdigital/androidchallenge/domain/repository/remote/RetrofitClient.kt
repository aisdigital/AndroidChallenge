package br.com.aisdigital.androidchallenge.domain.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val baseUrl = "https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io/"
    private const val DURATION = 60L

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient() = OkHttpClient().newBuilder()
        .addInterceptor { chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }
        .connectTimeout(DURATION, TimeUnit.SECONDS)
        .readTimeout(DURATION, TimeUnit.SECONDS)
        .writeTimeout(DURATION, TimeUnit.SECONDS)
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return getRetrofitInstance().create(serviceClass)
    }
}