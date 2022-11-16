package br.com.aisdigital.androidchallenge

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    val token: MutableLiveData<String> = MutableLiveData()

    private val service = RetrofitConfig.remoteServices

    fun getToken() {
        val call = service.auth()

        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                token.value = if (response.isSuccessful) {
                    response.body()?.token
                } else {
                    ""
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                token.value = ""
            }
        })
    }

    fun login(token: String?) {
        val call = service.login(token ?: "")

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }
        })
    }
}