package br.com.aisdigital.androidchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.core.domain.model.Result
import br.com.aisdigital.androidchallenge.core.domain.usecase.GetAllTeamsUseCase
import kotlinx.coroutines.launch

class TeamListViewModel(private val getAllTeamsUseCase: GetAllTeamsUseCase) : ViewModel() {

    private val _viewState = MutableLiveData<TeamListViewState>()
    val viewState: LiveData<TeamListViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            val result = getAllTeamsUseCase()
            if (result is Result.Success) {
                _viewState.value = TeamListViewState.ShowTeams(result.data)
            } else {
                _viewState.value = TeamListViewState.ShowError
            }
        }
    }
}