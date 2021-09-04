package br.com.aisdigital.androidchallenge.factory



import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.aisdigital.androidchallenge.helpers.Resources

import br.com.aisdigital.androidchallenge.repository.Repository
import kotlinx.coroutines.Dispatchers


class ViewModelTeams(
    var repository: Repository

) : ViewModel() {

    fun getTeams() = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(
                Resources.success(
                    data = repository.getTeamsRepository()
                )
            )

        } catch (exception: Exception) {
            emit(
                Resources.error(
                    data = null,
                    message = exception.message ?: "Error Ocurred"
                )
            )

        }
    }

    fun getUser(token:String) = liveData(Dispatchers.IO) {


        emit(Resources.loading(data = null))
        try {

            emit(
                Resources.success(
                    data = repository.getUserRepository(token)
                )
            )

        } catch (exception: Exception) {
            emit(
                Resources.error(
                    data = null,
                    message = exception.message ?: "Error Ocurred"
                )
            )

        }
    }


    fun getToken() = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {

            emit(
                Resources.success(
                    data = repository.getTokenRepository()
                )
            )

        } catch (exception: Exception) {
            emit(
                Resources.error(
                    data = null,
                    message = exception.message ?: "Error Ocurred"
                )
            )

        }
    }
}