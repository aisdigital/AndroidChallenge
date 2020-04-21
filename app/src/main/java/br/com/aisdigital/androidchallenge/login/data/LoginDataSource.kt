package br.com.aisdigital.androidchallenge.login.data

import br.com.aisdigital.androidchallenge.RetrofitClient
import br.com.aisdigital.androidchallenge.login.data.model.LoggedInUser
import io.reactivex.Observable
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    val client = RetrofitClient.getClient()
    fun login(username: String, password: String): Observable<LoggedInUser> {
        return client.auth(username, password).map { it }
    }
}

