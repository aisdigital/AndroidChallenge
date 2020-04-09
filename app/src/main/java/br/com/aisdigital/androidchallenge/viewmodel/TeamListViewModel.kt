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
    val noPostFoundVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    fun validateData() {
        teamList.value?.takeIf { it.isEmpty() }?.run {
            noPostFoundVisibility.value = getVisibility(true)
        }
    }

    private fun clearEmptyWarning() {
        noPostFoundVisibility.value = getVisibility(false)
    }

    fun getData() {
        clearEmptyWarning()

        setState(State.LOADING)

        viewModelScope.launch {
            try {
                teamList.value = repository.getTeams()
                validateData()

                setState(State.SUCCESS)
            } catch (ex: Exception) {
                setState(State.ERROR)
            }
        }
    }
}