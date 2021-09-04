

import br.com.aisdigital.androidchallenge.helpers.Consts.Companion.BASE_URL
import br.com.aisdigital.androidchallenge.model.TeamModel
import br.com.aisdigital.androidchallenge.model.User

import com.example.myapplication.model.Token

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface ApiClient {

    object RetrofitBuilder {

        private val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()

        private var getRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endpoints = getRetrofit.create(ApiClient::class.java)
    }

    @GET("/teams")
    suspend fun getTeams(): MutableList<TeamModel>

    @GET("/login")

    suspend fun  getUser(): User


    @POST("/auth")
    suspend fun getToken():Token

}