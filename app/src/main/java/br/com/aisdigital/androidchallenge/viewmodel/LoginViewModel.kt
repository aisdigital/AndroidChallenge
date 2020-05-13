package br.com.aisdigital.androidchallenge.viewmodel

import android.content.res.Resources
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.addOnPropertyChanged
import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import br.com.aisdigital.androidchallenge.utils.ButtonConfig
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: TeamRepository,
    private val router: AppRouter,
    private val resources: Resources
) :
    ViewModel() {

    val email = ObservableField("")
    val password = ObservableField("")

    val buttonConfig = ButtonConfig(resources.getString(R.string.login_button))

    private val _success: MutableLiveData<Boolean> = MutableLiveData(false)
    val success: LiveData<Boolean>
        get() = _success

    private val _errorMsg: MutableLiveData<String> = MutableLiveData("")
    val errorMsg: LiveData<String>
        get() = _errorMsg

    init {
        setupObservable()
        buttonConfig.disable()
    }

    private fun setupObservable() {
        email.addOnPropertyChanged {
            isValidInfo()
        }

        password.addOnPropertyChanged {
            isValidInfo()
        }
    }

    fun isValidEmail(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isValidPassword(password: String): Boolean = (password.length >= 4)

    private fun isValidInfo() {
        buttonConfig.run {
            if (isValidEmail(email.get().orEmpty()) && isValidPassword(password.get().orEmpty())) {
                enabled()
            } else {
                disable()
            }
        }
    }

    fun changeStatus(status: RequestStatus) {
        when (status) {
            RequestStatus.LOADING -> buttonConfig.showLoader()
            else -> {
                buttonConfig.dismissLoader()

                if (status == RequestStatus.ERROR) {
                    _errorMsg.value = resources.getString(R.string.generic_error)
                }
            }
        }
    }

    fun doLogin() {
        changeStatus(RequestStatus.LOADING)

        viewModelScope.launch {
            repository.doLogin(email.get().orEmpty(), password.get().orEmpty()).run {
                changeStatus(status)

                data?.let {
                    _success.value = true
                    router.goToHome(it)
                }
            }
        }
    }
}
