package br.com.aisdigital.androidchallenge.domain

import br.com.aisdigital.androidchallenge.domain.model.UserModel
import br.com.aisdigital.androidchallenge.repository.RepositoryImpl


class LoginUseCase() {

    private val repository: RepositoryImpl

    var onLoginReceived: (use: UserModel) -> Unit = {}

    init {
        repository = RepositoryImpl()
    }

    fun login(email: String, senha: String) {

        repository.login(email, senha, { autenticate(it) })
    }

    fun autenticate(token: String) {

        repository.onUserReceived = {
            val user = UserModel(it.name, it.age, it.gender)

            onLoginReceived(user)
        }

        repository.autentication(token)
    }
}