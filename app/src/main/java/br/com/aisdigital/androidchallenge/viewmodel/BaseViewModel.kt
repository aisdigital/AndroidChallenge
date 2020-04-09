package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.common.State

abstract class BaseViewModel : ViewModel() {
    val mainLoaderVisibility = MutableLiveData<Int>().apply {
        value = View.VISIBLE
    }
    val contendVisibility = MutableLiveData<Int>().apply {
        value = View.VISIBLE
    }
    val errorVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    fun getVisibility(show: Boolean): Int = takeIf { show }?.run {
        View.VISIBLE
    } ?: View.GONE

    fun setState(state: State) {
        contendVisibility.value =
            when (state) {
                State.SUCCESS -> getVisibility(true)
                State.ERROR, State.LOADING, State.REFRESH_LOADING -> getVisibility(
                    false
                )
            }

        mainLoaderVisibility.value =
            when (state) {
                State.LOADING -> getVisibility(true)
                State.SUCCESS, State.ERROR, State.REFRESH_LOADING -> getVisibility(
                    false
                )
            }

        errorVisibility.value =
            when (state) {
                State.ERROR -> getVisibility(true)
                State.SUCCESS, State.LOADING, State.REFRESH_LOADING -> getVisibility(
                    false
                )
            }
    }
}
