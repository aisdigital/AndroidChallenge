package br.com.aisdigital.androidchallenge

sealed class ViewState <out T>{

    object Loading : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Failed(val throwable: Throwable) : ViewState<Nothing>()
}