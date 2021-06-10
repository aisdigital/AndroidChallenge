package br.com.aisdigital.androidchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aisdigital.androidchallenge.domain.LoginUseCase
import br.com.aisdigital.androidchallenge.domain.model.LoginModel
import br.com.aisdigital.androidchallenge.domain.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val useCase: LoginUseCase = LoginUseCase()


    private val _user: MutableLiveData<UserModel> = MutableLiveData()

//    private val _login: MutableLiveData<LoginModel> = MutableLiveData()

    val user: LiveData<UserModel>
        get() = _user


    fun login(email: String, senha: String) {

        viewModelScope.launch(Dispatchers.IO) {
            useCase.apply {

                onLoginReceived = {
                    _user.postValue(it)
                }
                login(email, senha)
            }
        }
    }


//    fun autentication(token: String) {
//
//        viewModelScope.launch(Dispatchers.IO) {
//            useCase.apply {
//
//                onLoginReceived = {
//                    _user.postValue(it)
//                }
//                autenticate(token)
//            }
//        }
//    }


}