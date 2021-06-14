package br.com.aisdigital.androidchallenge.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.data.repository.AuthRepository
import br.com.aisdigital.androidchallenge.data.repository.BaseRepository
import br.com.aisdigital.androidchallenge.data.repository.HomeRepository
import br.com.aisdigital.androidchallenge.ui.auth.AuthViewModel
import br.com.aisdigital.androidchallenge.ui.home_user.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as HomeRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass not Found")
        }
    }
}