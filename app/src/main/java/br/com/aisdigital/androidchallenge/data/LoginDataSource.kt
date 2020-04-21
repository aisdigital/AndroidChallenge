package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.api.RetrofitClient
import br.com.aisdigital.androidchallenge.data.model.Login
import br.com.aisdigital.androidchallenge.data.model.User
import io.reactivex.Observable

class LoginDataSource {

    private val client = RetrofitClient.getClient()

    fun auth(username: String, password: String): Observable<Login> {
        return client.auth(username, password).map { it }
    }

    fun getUser(): Observable<User> {
        return client.getUser().map { it }
    }
}

