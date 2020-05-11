package br.com.aisdigital.androidchallenge.viewmodel

import androidx.lifecycle.*
import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.UserInfo
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TeamRepository, val userInfo: UserInfo?) :
    BaseViewModel(),
    LifecycleObserver {

    val teamList: LiveData<List<Team>>
        get() = _teamList
    private val _teamList: MutableLiveData<List<Team>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getTeamList() {
        changeState(RequestStatus.LOADING)

        viewModelScope.launch {
            repository.getTeams().run {
                _teamList.value = this.data
                changeState(this.status)
            }
        }
    }
}