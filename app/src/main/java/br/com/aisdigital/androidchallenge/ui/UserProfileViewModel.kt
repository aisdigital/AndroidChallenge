package br.com.aisdigital.androidchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.core.domain.usecase.CheckLoginStatusUseCase
import br.com.aisdigital.androidchallenge.core.domain.usecase.GetUserProfileUseCase
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _viewState: MutableLiveData<UserProfileViewState> = MutableLiveData()
    val viewState: LiveData<UserProfileViewState> get() = _viewState

    fun userProfile() {
        viewModelScope.launch {
            if (checkLoginStatusUseCase()) {
                _viewState.value = UserProfileViewState.ShowUserProfile(getUserProfileUseCase())
            } else {
                _viewState.value = UserProfileViewState.ShowLogin
            }
        }
    }
}