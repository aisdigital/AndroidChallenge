package br.com.aisdigital.androidchallenge.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.ui.activities.login.LoginViewModel
import br.com.aisdigital.androidchallenge.ui.activities.teams.TeamViewModel
import br.com.aisdigital.androidchallenge.service.Repository

class ViewModelFactory constructor(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TeamViewModel::class.java) -> {
                TeamViewModel(
                    this.repository
                ) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(
                    this.repository
                ) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}