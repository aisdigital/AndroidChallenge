package br.com.aisdigital.androidchallenge.ui

import br.com.aisdigital.androidchallenge.core.domain.model.Team

sealed class TeamListViewState {

    data class ShowTeams(val teams: List<Team>) : TeamListViewState()

    object ShowError : TeamListViewState()
}