package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TeamRepository) :
    ViewModel(),
    LifecycleObserver {

    val teamList: LiveData<List<Team>>
        get() = _teamList
    private val _teamList: MutableLiveData<List<Team>> = MutableLiveData()

    private val _mainLoaderVisibility = MutableLiveData<Int>().apply {
        value = View.VISIBLE
    }
    private val _contendVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }
    private val _errorVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }
    private val _emptyVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    val mainLoaderVisibility: LiveData<Int>
        get() = _mainLoaderVisibility
    val contendVisibility: LiveData<Int>
        get() = _contendVisibility
    val errorVisibility: LiveData<Int>
        get() = _errorVisibility
    val emptyVisibility: LiveData<Int>
        get() = _emptyVisibility

    private fun getVisibility(show: Boolean): Int = takeIf { show }?.run {
        View.VISIBLE
    } ?: View.GONE

    fun changeState(state: RequestStatus) {
        _contendVisibility.value =
            when (state) {
                RequestStatus.SUCCESS -> getVisibility(true)
                RequestStatus.ERROR, RequestStatus.LOADING -> getVisibility(
                    false
                )
            }

        _mainLoaderVisibility.value =
            when (state) {
                RequestStatus.LOADING -> getVisibility(true)
                RequestStatus.SUCCESS, RequestStatus.ERROR -> getVisibility(
                    false
                )
            }

        _errorVisibility.value =
            when (state) {
                RequestStatus.ERROR -> getVisibility(true)
                RequestStatus.SUCCESS, RequestStatus.LOADING -> getVisibility(
                    false
                )
            }
    }

    private fun verifyIsEmpty() {
        _teamList.value?.run {
            _emptyVisibility.value = getVisibility(this.isNullOrEmpty())
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getTeamList() {
        changeState(RequestStatus.LOADING)

        viewModelScope.launch {
            repository.getTeams().run {
                _teamList.value = this.data
                verifyIsEmpty()
                changeState(this.status)
            }
        }
    }
}
