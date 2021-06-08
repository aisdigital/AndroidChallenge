package br.com.aisdigital.androidchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.domain.TeamModel
import br.com.aisdigital.androidchallenge.domain.TeamUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val useCase: TeamUseCase

    init {
        useCase = TeamUseCase()
    }

    private val _teamList: MutableLiveData<List<TeamModel>> = MutableLiveData()
    val teamList: LiveData<List<TeamModel>>
        get() = _teamList

    fun getTeams(){

        viewModelScope.launch(Dispatchers.IO){
            _teamList.postValue(
                useCase.getTeamList()
            )
        }
    }


}