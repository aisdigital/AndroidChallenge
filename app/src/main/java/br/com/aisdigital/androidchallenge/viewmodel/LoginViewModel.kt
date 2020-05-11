package br.com.aisdigital.androidchallenge.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.model.Login
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: TeamRepository, private val router: AppRouter) :
    BaseViewModel() {
    val login: Login = Login()

    fun doLogin() {
        changeState(RequestStatus.LOADING)

        viewModelScope.launch {
            repository.doLogin(login).run {
                changeState(status)

                data?.let {
                    router.goToHome(it)
                }
            }
        }
    }

}