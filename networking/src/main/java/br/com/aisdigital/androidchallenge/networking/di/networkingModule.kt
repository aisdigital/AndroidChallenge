package br.com.aisdigital.androidchallenge.networking.di

import br.com.aisdigital.androidchallenge.domain.login.AuthService
import br.com.aisdigital.androidchallenge.domain.teams.FetchTeamService
import br.com.aisdigital.androidchallenge.networking.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networking = module {

    factory <AuthService> {
        FetchUserNetworkingService(restApi = get()) as AuthService
    }

    factory <FetchTeamService> {
        FetchTeamsNetworkingService(restApi = get()) as FetchTeamService
    }

    factory { providesOkHttpClient() }

    single {
        createWebService<UserIO>(RetrofitBuilder(RetrofitBuilder.BASE_URL, httpClient = get()))
    }

    single {
        createWebService<TeamsIO>(RetrofitBuilder(RetrofitBuilder.BASE_URL, httpClient = get()))
    }
}

fun providesOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T  = retrofit.create(T::class.java)

val networkingModule = listOf(networking)