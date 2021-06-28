package br.com.aisdigital.androidchallenge.module

import br.com.aisdigital.androidchallenge.retrofit.RequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ChallengeModule {

    @Provides
    fun provideBaseRequestService(
    ): RequestService {

        val httpClient = OkHttpClient.Builder();

        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-auth-token", "")
                .build();
            chain.proceed(request);
        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

        return retrofit.create(RequestService::class.java);
    }
}