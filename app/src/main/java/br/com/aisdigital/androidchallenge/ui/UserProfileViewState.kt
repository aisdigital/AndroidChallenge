package br.com.aisdigital.androidchallenge.ui

import br.com.aisdigital.androidchallenge.core.domain.model.User

sealed class UserProfileViewState {

    object ShowLogin : UserProfileViewState()

    data class ShowUserProfile(val user: User?) : UserProfileViewState()
}