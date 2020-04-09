package br.com.aisdigital.androidchallenge.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.common.State
import br.com.aisdigital.androidchallenge.domain.model.Team
import br.com.aisdigital.androidchallenge.domain.repository.AndroidChallengeRepository
import kotlinx.coroutines.launch

class TeamListViewModel(val repository: AndroidChallengeRepository) : BaseViewModel() {

    val teamList: MutableLiveData<List<Team>> = MutableLiveData()

    val noTeamFoundVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    private fun clearEmptyWarning() {
        noTeamFoundVisibility.value = getVisibility(false)
    }

    fun validateData() {
        teamList.value?.takeIf { it.isEmpty() }?.run {
            noTeamFoundVisibility.value = getVisibility(true)
        }
    }

    fun getData(isRefresh: Boolean = false) {
        clearEmptyWarning()

        takeIf { isRefresh }?.run {
            setState(State.REFRESH_LOADING)
        } ?: setState(State.LOADING)

        viewModelScope.launch {
            try {
                teamList.value = repository.getTeams()
                validateData()

                setState(State.SUCCESS)
            } catch (ex: Exception) {
//                Log.e(TAG_ERROR, ex.message.orEmpty())
                setState(State.ERROR)
            }
        }
    }
}