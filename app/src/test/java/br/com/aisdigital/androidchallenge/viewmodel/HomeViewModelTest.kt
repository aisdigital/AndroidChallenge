package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class HomeViewModelTest {

    private lateinit var sut: HomeViewModel
    private val repositoryMock: TeamRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        sut = HomeViewModel(repositoryMock)
    }

    @Test
    fun `when processing then the loader must be visible and the other components are not`() {
        sut.run {
            changeState(RequestStatus.LOADING)

            assertEquals(View.VISIBLE, mainLoaderVisibility.value)
            assertEquals(View.GONE, errorVisibility.value)
            assertEquals(View.GONE, emptyVisibility.value)
            assertEquals(View.GONE, contendVisibility.value)
        }
    }

    @Test
    fun `when an error occurs in the request then the error must be visible and the other components are not`() {
        sut.run {
            changeState(RequestStatus.ERROR)

            assertEquals(View.GONE, mainLoaderVisibility.value)
            assertEquals(View.VISIBLE, errorVisibility.value)
            assertEquals(View.GONE, emptyVisibility.value)
            assertEquals(View.GONE, contendVisibility.value)
        }
    }

    @Test
    fun `when you loaded the data then the content should be visible and the other components not`() {
        sut.run {
            changeState(RequestStatus.SUCCESS)

            assertEquals(View.GONE, mainLoaderVisibility.value)
            assertEquals(View.GONE, errorVisibility.value)
            assertEquals(View.GONE, emptyVisibility.value)
            assertEquals(View.VISIBLE, contendVisibility.value)
        }
    }
}