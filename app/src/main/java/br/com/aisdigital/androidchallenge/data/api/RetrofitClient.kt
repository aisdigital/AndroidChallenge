package br.com.aisdigital.androidchallenge.data.api

import okhttp3.Interceptor
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var service : Api? = null
    var serviceWithToken : Api? = null

    fun getClient(token : String): Api {

        serviceWithToken?.let {
            return it
        }

        var retrofit: Retrofit? = null

        val interceptorHeaders = HttpLoggingInterceptor()
        interceptorHeaders.level = HttpLoggingInterceptor.Level.HEADERS

        val interceptorBody = HttpLoggingInterceptor()
        interceptorBody.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptorHeaders)
            .addInterceptor(interceptorBody)
            .addInterceptor(HeaderInterceptor(token))
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        var url = "https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io/"
        retrofit = Retrofit.Builder()
            .baseUrl(url)

            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()


        serviceWithToken = retrofit.create<Api>(Api::class.java)
        service = serviceWithToken
        return serviceWithToken!!
    }

    fun getClient(): Api {

        serviceWithToken?.let {
            return it
        }

        service?.let {
            return it
        }

        var retrofit: Retrofit? = null

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)

            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
        var url = "https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io/"
        retrofit = Retrofit.Builder()
            .baseUrl(url)

            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()


        service = retrofit.create<Api>(Api::class.java)

        return service!!
    }

    class HeaderInterceptor(private val xAuthToken : String): Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request?.newBuilder()
                ?.addHeader("x-auth-token", xAuthToken)
                ?.build()
            return chain.proceed(request)
        }
    }
}