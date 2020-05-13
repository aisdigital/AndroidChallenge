package br.com.aisdigital.androidchallenge.utils

import android.view.View
import br.com.aisdigital.androidchallenge.R
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ButtonConfigTest {

    private var sut = ButtonConfig("Xablau")

    @Test
    fun `when to disable the button, then change the disable button`() {
        sut.run {
            disable()

            assertEquals(false, enabled.value)
            assertEquals(R.color.grey, buttonColor.value)
        }
    }

    @Test
    fun `when I enable the button then I must change the color to enable the button`() {
        sut.run {
            enabled()

            assertEquals(true, enabled.value)
            assertEquals(R.color.colorAccent, buttonColor.value)
        }
    }

    @Test
    fun `when I show it the loader should hide the text and disable the button`() {
        sut.run {
            showLoader()

            assertEquals(false, enabled.value)
            assertEquals(View.VISIBLE, loaderVisibility.value)
            assertEquals(View.GONE, textVisibility.value)
        }
    }

    @Test
    fun `when I remove the loader it should show the text and enable the button`() {
        sut.run {
            showLoader()

            assertEquals(true, enabled.value)
            assertEquals(View.GONE, loaderVisibility.value)
            assertEquals(View.VISIBLE, textVisibility.value)
        }
    }

}