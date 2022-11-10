package br.com.aisdigital.androidchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.model.ResultApi
import br.com.aisdigital.androidchallenge.domain.AuthenticateUsecase
import br.com.aisdigital.androidchallenge.domain.ClearLoginLocalDataUsecase
import br.com.aisdigital.androidchallenge.domain.LoginUsecase
import br.com.aisdigital.androidchallenge.domain.ValidateEmailUsecase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUsecase: LoginUsecase,
    private val authenticateUsecase: AuthenticateUsecase,
    private val validateEmailUsecase: ValidateEmailUsecase,
    private val clearLoginLocalDataUsecase: ClearLoginLocalDataUsecase
) : ViewModel() {
    private val loadingState: LoadingState by lazy {
        LoadingState()
    }
    private val validationErrorState: ValidationErrorState by lazy {
        ValidationErrorState()
    }
    private val loginResultState: LoginResultState by lazy {
        LoginResultState()
    }

    private val loadingStateMutableLiveData = MutableLiveData<LoadingState>()
    val loadingStateLiveData: LiveData<LoadingState> get() = loadingStateMutableLiveData

    private val validationErrorMutableLiveData = MutableLiveData<ValidationErrorState>()
    val validationErrorStateLiveData: LiveData<ValidationErrorState> get() = validationErrorMutableLiveData

    private val loginResultMutableLiveData = MutableLiveData<LoginResultState>()
    val loginResultStateLiveData: LiveData<LoginResultState> get() = loginResultMutableLiveData

    fun authenticate(email: String?, password: String?) {
        if (validateFields(email, password)) {
            viewModelScope.launch {
                setLoadingState(true)
                when (authenticateUsecase.authenticate(email!!, password!!)) {
                    is ResultApi.Success -> {
                        login()
                    }
                    is ResultApi.Error -> {
                        setLoadingState(isLoading = false)
                        setLoginResultState(success = false)
                    }
                }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            when (val result = loginUsecase.login()) {
                is ResultApi.Success -> {
                    setLoadingState(isLoading = false)
                    setLoginResultState(success = true, result.data.name)
                }
                is ResultApi.Error -> {
                    setLoadingState(isLoading = false)
                    setLoginResultState(success = false)
                }
            }
        }
    }

    fun clearLocalData() {
        clearLoginLocalDataUsecase.clearLocalData()
    }

    private fun validateFields(email: String?, password: String?): Boolean {
        resetValidationErrorState()
        var isValid = true
        if (!validateEmailUsecase.isValidEmail(email)) {
            setEmailValidationErrorState(R.string.email_validation_error)
            isValid = false
        }
        if (password.isNullOrEmpty()) {
            setPasswordValidationErrorState(R.string.password_validation_error)
            isValid = false
        }
        return isValid
    }

    private fun setLoginResultState(success: Boolean, clientName: String? = null) {
        loginResultState.success = success
        loginResultState.clientName = clientName
        loginResultMutableLiveData.value = loginResultState
    }

    private fun setLoadingState(isLoading: Boolean) {
        loadingState.isLoading = isLoading
        loadingStateMutableLiveData.value = loadingState
    }

    private fun setEmailValidationErrorState(
        emailValidationErrorResourceId: Int,
    ) {
        validationErrorState.emailValidationErrorResourceId = emailValidationErrorResourceId
        validationErrorMutableLiveData.value = validationErrorState
    }

    private fun setPasswordValidationErrorState(
        passwordValidationErrorResourceId: Int,
    ) {
        validationErrorState.passwordValidationErrorResourceId = passwordValidationErrorResourceId
        validationErrorMutableLiveData.value = validationErrorState
    }

    private fun resetValidationErrorState() {
        validationErrorState.emailValidationErrorResourceId = R.string.empty
        validationErrorState.passwordValidationErrorResourceId = R.string.empty
        validationErrorMutableLiveData.value = validationErrorState
    }
}

data class LoadingState(
    var isLoading: Boolean = false
)

data class ValidationErrorState(
    var emailValidationErrorResourceId: Int = R.string.empty,
    var passwordValidationErrorResourceId: Int = R.string.empty
)

data class LoginResultState(
    var success: Boolean = false,
    var clientName: String? = null,
    var successMessageResourceId: Int = R.string.success_message,
    var errorMessageResourceId: Int = R.string.api_error_message
)