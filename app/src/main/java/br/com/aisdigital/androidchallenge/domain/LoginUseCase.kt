package br.com.aisdigital.androidchallenge.domain

import br.com.aisdigital.androidchallenge.repository.RepositoryImpl


class LoginUseCase() {

    private val repository: RepositoryImpl

//    var onLoginReceived: (use: UserModel) -> Unit = {}

    init {
        repository = RepositoryImpl()
    }

    fun login(email: String, senha: String) {

        val tokenR = repository.login(email, senha)
        repository.onTokenReceived = { token ->
//            autenticate(token)

        }


    }

    private fun autenticate() {


    }
}