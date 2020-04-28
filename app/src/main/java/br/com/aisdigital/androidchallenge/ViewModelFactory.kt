package br.com.aisdigital.androidchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.domain.teams.FetchTeams
import br.com.aisdigital.androidchallenge.home.TeamsViewModel
import kotlinx.coroutines.CoroutineDispatcher

class ViewModelFactory(private val usecase: FetchTeams, private val ioDispatcher: CoroutineDispatcher) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if(modelClass.isAssignableFrom(TeamsViewModel::class.java)) {
                TeamsViewModel(usecase, ioDispatcher) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
    }
}