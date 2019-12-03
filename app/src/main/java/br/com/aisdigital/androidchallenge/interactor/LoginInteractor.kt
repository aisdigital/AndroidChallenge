package br.com.aisdigital.androidchallenge.interactor

import android.content.Context
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.domain.Auth
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.domain.User
import br.com.aisdigital.androidchallenge.repository.AuthRepository
import br.com.aisdigital.androidchallenge.repository.LoginRepository
import io.reactivex.Single

class LoginInteractor (context:Context) {

    //TODO: Get Base URL from configuration
    private val loginRepository = LoginRepository(context, context.resources.getString(R.string.base_url))

    fun login(): Single<User> {
        return loginRepository.login()
    }

}