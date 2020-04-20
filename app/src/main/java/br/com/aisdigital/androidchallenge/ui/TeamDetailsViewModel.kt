package br.com.aisdigital.androidchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.core.domain.model.Team
import br.com.aisdigital.androidchallenge.core.domain.usecase.GetTeamUseCase
import kotlinx.coroutines.launch

class TeamDetailsViewModel(private val getTeamUseCase: GetTeamUseCase) : ViewModel() {

    private val _team: MutableLiveData<Team> = MutableLiveData()
    val team: LiveData<Team> get() = _team

    fun loadTeamDetails(teamId: Long) {
        viewModelScope.launch {
            _team.value = getTeamUseCase(teamId)
        }
    }
}