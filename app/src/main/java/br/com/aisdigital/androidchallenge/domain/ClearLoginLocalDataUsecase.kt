package br.com.aisdigital.androidchallenge.domain

import br.com.aisdigital.androidchallenge.data.LoginRepository

class ClearLoginLocalDataUsecase(private val loginRepository: LoginRepository) {
    fun clearLocalData() {
        loginRepository.clearLocalData()
    }
}