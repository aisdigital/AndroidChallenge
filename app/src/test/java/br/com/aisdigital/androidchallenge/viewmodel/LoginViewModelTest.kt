package br.com.aisdigital.androidchallenge.viewmodel

import android.content.Context
import android.view.View
import androidx.test.core.app.ApplicationProvider
import br.com.aisdigital.androidchallenge.internal.AppRouter
import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.repository.TeamRepository
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {

    private lateinit var sut: LoginViewModel
    private val repositoryMock: TeamRepository = mockk(relaxed = true)
    private val routerMock: AppRouter = mockk(relaxed = true)
    private val resourcesMock = (ApplicationProvider.getApplicationContext() as Context).resources

    @Before
    fun setup() {
        sut = LoginViewModel(
            repositoryMock,
            routerMock,
            resourcesMock
        )
    }

    @Test
    fun `when I inform an invalid email then it should return negatively`() {
        sut.run {
            val test = isValidEmail("x@11111")

            assertEquals(false, test)
        }
    }

    @Test
    fun `when I enter an invalid password then it should return negatively`() {
        sut.run {
            val test = isValidPassword("111")

            assertEquals(false, test)
        }
    }

    @Test
    fun `when I inform an invalid email and password then the button must be disabled`() {
        sut.run {
            email.set("xx@aaaa")
            password.set("111")

            assertEquals(false, buttonConfig.enabled.value)
        }
    }

    @Test
    fun `when I inform a valid email and password then the button must be enabled`() {
        sut.run {
            email.set("user@gmail.com")
            password.set("1234")

            assertEquals(true, buttonConfig.enabled.value)
        }
    }

    @Test
    fun `when processing the login then the loader should appear`() {
        sut.run {
            changeStatus(RequestStatus.LOADING)

            assertEquals(View.VISIBLE, buttonConfig.loaderVisibility.value)
        }
    }

    @Test
    fun `when an error occurs when logging in then it should show an error message and the loader is not visible`() {
        sut.run {
            changeStatus(RequestStatus.ERROR)

            assertEquals("Ocorreu um problema. Tente novamente!", errorMsg.value)
        }
    }

}