package br.com.aisdigital.androidchallenge.service

import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.Token
import br.com.aisdigital.androidchallenge.model.User
import io.reactivex.Single

class RepositoryImpl(private val service: ApiService) : Repository {

    override fun getTeams() = service.getTeams()
    override fun auth(mail: String, pass: String): Single<Token> {
        return service.auth(mail, pass)
    }

    override fun login(token: String): Single<User> {
        return service.login(token)
    }
}