package br.com.aisdigital.androidchallenge.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.App
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.extensions.*
import br.com.aisdigital.androidchallenge.model.FieldError
import br.com.aisdigital.androidchallenge.model.enums.Constants
import br.com.aisdigital.androidchallenge.repository.BackendRepository
import br.com.aisdigital.androidchallenge.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _success = MutableLiveData<Boolean>()
    val success: MutableLiveData<Boolean>
        get() = _success

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _user = MutableLiveData<String>()
    val user: LiveData<String>
        get() = _user

    fun setUser(value: String) {
        _user.postValue(value)
    }

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun setPassword(value: String) {
        _password.postValue(value)
    }

    private val _data = MutableLiveData<User>()
    val data: LiveData<User>
        get() = _data

    @Inject
    lateinit var backendRepository: BackendRepository

    @Inject
    lateinit var preferences: SharedPreferences

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var resources: Resources

    init {
        getApplication<App>().component.inject(this)
    }

    fun postLogin(): List<FieldError> {
        val checkedFields = assertFields()
        if (checkedFields.isEmpty()) {
            viewModelScope.launch {
                try {
                    _loading.postValue(true)
                    val token =
                        backendRepository.postAuthAsync(_user.value!!, _password.value!!)

                    preferences.edit()
                        .putString(
                            Constants.TOKEN.value,
                            token.token
                        )
                        .apply()

                    val response =
                        backendRepository.getLoginAsync()

                    preferences.edit()
                        .putString(
                            Constants.NAME.value,
                            response.name
                        )
                        .putString(
                            Constants.AGE.value,
                            response.age
                        )
                        .putString(
                            Constants.GENDER.value,
                            response.gender
                        )
                        .apply()

                    _success.postValue(true)
                    _loading.postValue(false)

                } catch (e: Exception) {
                    _loading.postValue(false)
                    _error.postValue(resources.getString(R.string.msg_erro_http))
                }
            }
        }

        return checkedFields
    }

    private fun assertFields(): List<FieldError> {
        val fields = arrayListOf<FieldError>()

        // Verificar se User esta vazio
        if (_user.value.isNullOrBlank()) {
            fields.add(FieldError(R.id.til_user, R.string.msg_user_obrigatorio))
        } else
        // Válidar E-mail
            if (!_user.value.isValidEmail()) {
                fields.add(FieldError(R.id.til_user, R.string.msg_user_email_invalido))
            }

        // Verificar se Password esta vazio
        if (_password.value.isNullOrBlank()) {
            fields.add(FieldError(R.id.til_password, R.string.msg_password_obrigatorio))
        } else
        // Verificar se o password atende as validações
            if (!_password.value.isValidPassword()) {
                fields.add(FieldError(R.id.til_password, R.string.msg_password_invalido))
            }

        return fields
    }
}
