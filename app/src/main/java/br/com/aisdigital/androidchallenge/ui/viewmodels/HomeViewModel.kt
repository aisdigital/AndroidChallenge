package br.com.aisdigital.androidchallenge.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.aisdigital.androidchallenge.entities.Team
import br.com.aisdigital.androidchallenge.entities.User
import br.com.aisdigital.androidchallenge.repositories.LoginRepository
import br.com.aisdigital.androidchallenge.repositories.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: TeamRepository,
    var loginRepository: LoginRepository
) : ViewModel() {

    private var _teams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>>
        get() = _teams

    private var _user = MutableLiveData<User?>()
    val user: LiveData<User?>
        get() = _user

    init {
        _user = loginRepository.getLoggedUser().asLiveData() as MutableLiveData<User?>
        _teams = repository.getTeams().asLiveData() as MutableLiveData<List<Team>>
    }

    fun logout() {
        loginRepository.logout()
    }
}