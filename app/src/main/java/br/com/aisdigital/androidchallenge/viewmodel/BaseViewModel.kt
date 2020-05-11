package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.aisdigital.androidchallenge.internal.RequestStatus

open class BaseViewModel : ViewModel() {

    private val _mainLoaderVisibility = MutableLiveData<Int>().apply {
        value = View.VISIBLE
    }
    private val _contendVisibility = MutableLiveData<Int>().apply {
        value = View.VISIBLE
    }
    private val _errorVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    val mainLoaderVisibility: LiveData<Int>
        get() = _mainLoaderVisibility
    val contendVisibility: LiveData<Int>
        get() = _contendVisibility
    val errorVisibility: LiveData<Int>
        get() = _errorVisibility

    fun getVisibility(show: Boolean): Int = takeIf { show }?.run {
        View.VISIBLE
    } ?: View.GONE

    fun changeState(state: RequestStatus) {
        _contendVisibility.value =
            when (state) {
                RequestStatus.SUCCESS -> getVisibility(true)
                RequestStatus.ERROR, RequestStatus.LOADING -> getVisibility(
                    false
                )
            }

        _mainLoaderVisibility.value =
            when (state) {
                RequestStatus.LOADING -> getVisibility(true)
                RequestStatus.SUCCESS, RequestStatus.ERROR -> getVisibility(
                    false
                )
            }

        _errorVisibility.value =
            when (state) {
                RequestStatus.ERROR -> getVisibility(true)
                RequestStatus.SUCCESS, RequestStatus.LOADING -> getVisibility(
                    false
                )
            }
    }

}