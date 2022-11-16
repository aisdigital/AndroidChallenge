package br.com.aisdigital.androidchallenge.viewmodel

import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.repository.TeamsRepository

class TeamsViewModel : ViewModel() {

    private val repository = TeamsRepository()

    fun getTeams() {
        repository.getTeams()
    }
}