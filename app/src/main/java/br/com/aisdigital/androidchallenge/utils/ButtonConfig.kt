package br.com.aisdigital.androidchallenge.utils

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.R

class ButtonConfig(val text: String) {

    private val _loaderVisibility = MutableLiveData(View.GONE)
    private val _textVisibility = MutableLiveData(View.VISIBLE)
    private val _buttonColor = MutableLiveData(R.color.colorAccent)
    private val _enabled = MutableLiveData(true)

    val loaderVisibility: LiveData<Int>
        get() = _loaderVisibility
    val textVisibility: LiveData<Int>
        get() = _textVisibility
    val buttonColor: LiveData<Int>
        get() = _buttonColor
    val enabled: LiveData<Boolean>
        get() = _enabled

    fun showLoader() {
        _loaderVisibility.value = View.VISIBLE
        _textVisibility.value = View.GONE
        _enabled.value = false
    }

    fun dismissLoader() {
        _loaderVisibility.value = View.GONE
        _textVisibility.value = View.VISIBLE
        _enabled.value = true
    }

    fun disable() {
        _enabled.value = false
        _buttonColor.value = R.color.grey
    }

    fun enabled() {
        _enabled.value = true
        _buttonColor.value = R.color.colorAccent
    }
}
