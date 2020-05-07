package br.com.aisdigital.androidchallenge.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.ViewState
import br.com.aisdigital.androidchallenge.domain.login.AuthenticateUser
import br.com.aisdigital.androidchallenge.domain.login.Login
import br.com.aisdigital.androidchallenge.domain.login.LoginDataSource
import br.com.aisdigital.androidchallenge.domain.login.LoginValidator
import br.com.aisdigital.androidchallenge.domain.user.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginValidator: LoginValidator,
    private val usecase: AuthenticateUser,
    private val dataSource: LoginDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    var emailAddress: String = ""
    var userPassword: String = ""

    private val _loginState = MutableLiveData<ViewState<Login>>()
    val loginState: LiveData<ViewState<Login>> get() = _loginState

    fun hasUserLoggedIn(): Boolean {
        return dataSource.isUserLoggedIn()
    }

    fun getUserLoggedIn(): User? {
        return dataSource.getUser()
    }

    fun authenticate() {
        val loginEntity = Login(emailAddress, userPassword)
        _loginState.apply { value = ViewState.Loading }

        if(!loginValidator.isValid(loginEntity)) {
            _loginState.postValue(ViewState.Failed(Exception("Login e/ou Senha inválidos")))
        } else {
            viewModelScope.launch(ioDispatcher) {
                try {
                    val result = usecase.doAuthentication(loginEntity)

                    getUserByLogin(result)

                } catch(e: Throwable) {
                    _loginState.postValue(ViewState.Failed(e))
                }
            }
        }
    }

    private fun getUserByLogin(login: Login) {
        viewModelScope.launch(ioDispatcher) {
            val user = usecase.login(login)
            dataSource.save(user)
            _loginState.postValue(ViewState.Success(login))
        }
    }

    fun logout() {
        dataSource.clear()
    }
}