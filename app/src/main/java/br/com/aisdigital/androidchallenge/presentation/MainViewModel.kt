package br.com.aisdigital.androidchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.domain.model.TeamModel
import br.com.aisdigital.androidchallenge.domain.TeamUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val useCase: TeamUseCase = TeamUseCase()

    private val _teamList: MutableLiveData<List<TeamModel>> = MutableLiveData()
    val teamList: LiveData<List<TeamModel>>
        get() = _teamList

    fun getTeams() {

        viewModelScope.launch(Dispatchers.IO) {
            useCase.apply {
                onListReceived = {
                    _teamList.postValue(it)
                }
                getTeamList()
            }
        }
    }
}