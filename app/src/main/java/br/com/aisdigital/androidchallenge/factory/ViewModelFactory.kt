package br.com.aisdigital.androidchallenge.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.repository.Repository

class ViewModelFactory(
    private val repository: Repository

) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ViewModelTeams::class.java) -> {
                return ViewModelTeams(
                    repository
                ) as T
            }
            else -> {
                throw  IllegalArgumentException("Class not found")
            }
        }
    }
}