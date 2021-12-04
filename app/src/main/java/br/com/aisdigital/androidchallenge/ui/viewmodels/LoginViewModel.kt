package br.com.aisdigital.androidchallenge.ui.viewmodels

import androidx.lifecycle.*
import br.com.aisdigital.androidchallenge.entities.User
import br.com.aisdigital.androidchallenge.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var repository: LoginRepository) : ViewModel() {

    private var _loginUser = MutableLiveData<User?>()
    val loginUser: LiveData<User?>
        get() = _loginUser

    init {
        _loginUser = repository.getLoggedUser().asLiveData() as MutableLiveData<User?>
    }

    fun login(email: String, password: String) {
        repository.login(email, password)
    }
}