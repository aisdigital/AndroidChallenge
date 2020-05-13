package br.com.aisdigital.androidchallenge.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import br.com.aisdigital.androidchallenge.R

fun FragmentActivity.swipeLeftTransition() {
    this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun FragmentActivity.swipeRightTransition() {
    this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
}

fun FragmentActivity.fadeTransition() {
    this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

fun FragmentActivity.hideKeyboard() {
    val view: View? = this.currentFocus

    view?.let {
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            it.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}
