package br.com.aisdigital.androidchallenge.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofitinstance {
    companion object{
        fun getRetrofitInstance():Retrofit{
            var address = Address()
            return Retrofit.Builder()
                .baseUrl(address.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}