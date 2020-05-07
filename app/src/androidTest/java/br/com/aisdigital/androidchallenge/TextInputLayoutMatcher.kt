package br.com.aisdigital.androidchallenge

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class TextInputLayoutMatcher(private val expectedErrorText: String) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("TextInputLayout custom Matcher")
    }

    override fun matchesSafely(view: View?): Boolean {
        if(view !is TextInputLayout){
            return false
        }

        val error = view.error

       return error?.let {
            error.toString() == expectedErrorText
        } ?: false
    }

    companion object {
        fun hasTextInputLayoutHintText(expectedErrorText: String) : TextInputLayoutMatcher {
            return TextInputLayoutMatcher(expectedErrorText)
        }
    }
}
