package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.view.component.MyLoader

open class BaseViewModel : ViewModel() {

    val requestState = MediatorLiveData<RequestState<Any>>()

    val contentVisibility = MediatorLiveData<Int>().apply {
        addSource(requestState) {
            value = when (it) {
                is RequestState.Success, RequestState.Idle -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    val loaderState = MediatorLiveData<MyLoader.State>().apply {
        addSource(requestState) {
            value = when (it) {
                is RequestState.Error -> MyLoader.State.ERROR
                is RequestState.Loading -> MyLoader.State.LOADING
                is RequestState.Success, RequestState.Idle -> MyLoader.State.COMPLETE
            }
        }
    }

}