package br.com.aisdigital.androidchallenge.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.TeamPresentation
import br.com.aisdigital.androidchallenge.ViewState
import br.com.aisdigital.androidchallenge.domain.teams.FetchTeams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TeamsViewModel(
    private val useCase: FetchTeams,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _teams = MutableLiveData<ViewState<List<TeamPresentation>>>().apply {
        value = ViewState.Loading
    }
    val teams: LiveData<ViewState<List<TeamPresentation>>>
    get() = _teams

    fun loadTeams() {
        _teams.postValue(ViewState.Loading)
        viewModelScope.launch(ioDispatcher) {
            try {
                _teams.postValue(ViewState.Success<List<TeamPresentation>>(useCase.listAll().map { TeamPresentation(
                    it.name,
                    it.city,
                    it.conference,
                    it.image,
                    it.descr
                ) }))
            } catch (e: Throwable) {
                _teams.postValue(ViewState.Failed(e))
            }
        }
    }
}