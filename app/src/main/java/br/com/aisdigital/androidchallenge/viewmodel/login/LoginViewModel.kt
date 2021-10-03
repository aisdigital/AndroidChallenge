package br.com.aisdigital.androidchallenge.viewmodel.login

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.repository.interactor.AuthInteractor
import br.com.aisdigital.androidchallenge.domain.repository.interactor.LoginInteractor
import br.com.aisdigital.androidchallenge.domain.repository.Router
import br.com.aisdigital.androidchallenge.viewmodel.BaseViewModel
import br.com.aisdigital.androidchallenge.domain.utils.isEmailValid
import br.com.aisdigital.androidchallenge.domain.utils.isPasswordValid

class LoginViewModel(
    private val router: Router,
    private val authInteractor: AuthInteractor,
    private val loginInteractor: LoginInteractor,
    private val resources: Resources
) : BaseViewModel() {

    val emailInputText = MutableLiveData<String>()
    val emailHelperText = MutableLiveData<String>()
    val passwordInputText = MutableLiveData<String>()
    val passwordHelperText = MutableLiveData<String>()

    init {
        observeAuthInteractor()
        observeLoginInteractor()
    }

    private fun observeAuthInteractor() {
        requestState.addSource(authInteractor.requestState) {
            requestState.value = it

            if (it is RequestState.Success) {
                loginInteractor.doLogin(it.result.token)
            }
        }
    }

    private fun observeLoginInteractor() {
        requestState.addSource(loginInteractor.requestState) {
            requestState.value = it

            if (it is RequestState.Success) {
                router.navigateToHome(it.result)
            }
        }
    }

    fun onLoginClick() {
        if (validateFields())
            authInteractor.doAuth(emailInputText.value.orEmpty(), emailInputText.value.orEmpty())
    }

    private fun validateFields(): Boolean {
        val emailValid = isEmailValid()
        val passwordValid = isPasswordValid()

        return emailValid && passwordValid
    }

    fun onEmailTextChanged() {
        emailHelperText.postValue(null)
    }

    fun onPasswordTextChanged() {
        passwordHelperText.postValue(null)
    }

    private fun isEmailValid(): Boolean {
        return if (emailInputText.value.isEmailValid()) {
            true
        } else {
            emailHelperText.postValue(resources.getString(R.string.login_helper_email))
            false
        }
    }

    private fun isPasswordValid(): Boolean {
        return if (passwordInputText.value.isPasswordValid()) {
            true
        } else {
            passwordHelperText.postValue(resources.getString(R.string.login_helper_password))
            false
        }
    }

}