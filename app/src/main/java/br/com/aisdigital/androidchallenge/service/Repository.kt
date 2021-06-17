package br.com.aisdigital.androidchallenge.service

import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.Token
import br.com.aisdigital.androidchallenge.model.User
import io.reactivex.Single

interface Repository {
     fun getTeams(): Single<List<Team>>
     fun auth(mail : String, pass : String): Single<Token>
     fun login(token : String) : Single<User>
}