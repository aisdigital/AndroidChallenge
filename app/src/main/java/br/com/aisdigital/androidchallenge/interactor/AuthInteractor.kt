package br.com.aisdigital.androidchallenge.interactor

import android.content.Context
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.domain.Auth
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.repository.AuthRepository
import io.reactivex.Single

class AuthInteractor(context: Context) {

    private val authRepository = AuthRepository(context, context.resources.getString(R.string.base_url))

    fun authenticate(auth: Auth): Single<Session> {
        return authRepository.auth(auth)
    }

}