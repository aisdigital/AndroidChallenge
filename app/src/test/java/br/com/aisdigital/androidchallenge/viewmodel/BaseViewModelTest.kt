package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import br.com.aisdigital.androidchallenge.common.State
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BaseViewModelTest {

    lateinit var sut: BaseViewModel

    @Before
    fun setup() {
        sut = BaseViewModel()
    }

    @Test
    fun `when you instantiate the viewModel then these are the initial values`() {
        sut.run {
            Assert.assertEquals(View.VISIBLE, mainLoaderVisibility.value)
            Assert.assertEquals(View.VISIBLE, contendVisibility.value)
            Assert.assertEquals(View.GONE, errorVisibility.value)
        }
    }

    @Test
    fun `when I'm loading the content then these are the expected values`() {
        sut.run {
            setState(State.LOADING)

            Assert.assertEquals(View.VISIBLE, mainLoaderVisibility.value)
            Assert.assertEquals(View.GONE, contendVisibility.value)
            Assert.assertEquals(View.GONE, errorVisibility.value)
        }
    }

    @Test
    fun `when you loaded the content so these are the expected values`() {
        sut.run {
            setState(State.SUCCESS)

            Assert.assertEquals(View.GONE, mainLoaderVisibility.value)
            Assert.assertEquals(View.VISIBLE, contendVisibility.value)
            Assert.assertEquals(View.GONE, errorVisibility.value)
        }
    }

    @Test
    fun `when an error occurs when loading the content then these are the expected values`() {
        sut.run {
            setState(State.ERROR)

            Assert.assertEquals(View.GONE, mainLoaderVisibility.value)
            Assert.assertEquals(View.GONE, contendVisibility.value)
            Assert.assertEquals(View.VISIBLE, errorVisibility.value)
        }
    }

}
