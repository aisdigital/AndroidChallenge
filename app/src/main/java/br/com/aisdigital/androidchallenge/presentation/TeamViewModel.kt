package br.com.aisdigital.androidchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val repository: TeamRepository
) : ViewModel() {

    fun getTeams() : LiveData<List<Team>> {
        return repository.getTeams()
    }
}