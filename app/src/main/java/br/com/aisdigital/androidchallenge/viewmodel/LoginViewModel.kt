package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.common.State
import br.com.aisdigital.androidchallenge.domain.model.AuthToken
import br.com.aisdigital.androidchallenge.domain.model.User
import br.com.aisdigital.androidchallenge.domain.repository.AndroidChallengeRepository
import kotlinx.coroutines.launch

class LoginViewModel(val repository: AndroidChallengeRepository): BaseViewModel() {

    val authToken: MutableLiveData<AuthToken> = MutableLiveData()
    val user: MutableLiveData<User> = MutableLiveData()
    val userEmail: MutableLiveData<String> = MutableLiveData()
    val userPassword: MutableLiveData<String> = MutableLiveData()

    val invalidDataVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    fun validateData(email: String, password: String): Boolean {
        val isEmailDataEmpty = email.isEmpty()
        val isPasswordDataEmpty = password.isEmpty()

        invalidDataVisibility.apply {
            value = when {
                isEmailDataEmpty || isPasswordDataEmpty -> getVisibility(true)
                else -> getVisibility(false)
            }
        }

        return !isEmailDataEmpty && !isPasswordDataEmpty

    }

    fun login() {

        if(validateData(userEmail.value?: "", userPassword.value?: "")) {
            setState(State.LOADING)
            viewModelScope.launch {
                try {
                    authToken.value =
                        repository.getAuthToken(userEmail.value.orEmpty(), userPassword.value.orEmpty())
                    authToken.value?.let {
                        user.value = repository.getUser(it.token ?: "")
                    }

                    setState(State.SUCCESS)
                } catch (ex: Exception) {
                    setState(State.ERROR)
                }
            }
        }
    }

    fun hideErrorLayout() = setState(State.SUCCESS)
}