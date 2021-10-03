package br.com.aisdigital.androidchallenge.viewmodel.home

import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.domain.model.teams.TeamsResponse
import br.com.aisdigital.androidchallenge.domain.repository.interactor.TeamsInteractor
import br.com.aisdigital.androidchallenge.viewmodel.BaseViewModel

class HomeViewModel(
    private val teamsInteractor: TeamsInteractor
) : BaseViewModel() {

    val userName = MutableLiveData<String>()
    val userAge = MutableLiveData<String>()
    val userGender = MutableLiveData<String>()
    val teamsList = MutableLiveData<List<TeamsResponse>>()

    init {
        observeTeamsInteractor()
    }

    fun load(loginResponse: LoginResponse) {
        userName.postValue(loginResponse.name)
        userAge.postValue(loginResponse.age)
        userGender.postValue(loginResponse.gender)
    }

    private fun observeTeamsInteractor() {
        requestState.addSource(teamsInteractor.requestState) {
            requestState.value = it

            if (it is RequestState.Success) {
                teamsList.postValue(it.result)
            }
        }
    }

    fun onResume() {
        getTeams()
    }

    fun getTeams() {
        teamsInteractor.getTeams()
    }

}