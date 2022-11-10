package br.com.aisdigital.androidchallenge.helper

import androidx.lifecycle.*


fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = SingleTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}

class SingleTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        handler(t)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}
