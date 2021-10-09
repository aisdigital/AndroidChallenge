package br.com.aisdigital.androidchallenge.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.aisdigital.androidchallenge.TeamViewModel
import br.com.aisdigital.androidchallenge.service.Repository

class MyViewModelFactory constructor(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            TeamViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}