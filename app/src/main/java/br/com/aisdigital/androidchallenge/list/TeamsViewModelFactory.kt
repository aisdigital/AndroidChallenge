package br.com.aisdigital.androidchallenge.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.data.LoginDataSource
import br.com.aisdigital.androidchallenge.data.LoginRepository
import br.com.aisdigital.androidchallenge.data.TeamsDataSource
import br.com.aisdigital.androidchallenge.data.TeamsRepository
import br.com.aisdigital.androidchallenge.list.TeamsViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class TeamsViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamsViewModel::class.java)) {
            return TeamsViewModel(
                teamRepository = TeamsRepository(
                    dataSource = TeamsDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
