package br.com.aisdigital.androidchallenge.viewmodel.login

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.model.auth.AuthResponse
import br.com.aisdigital.androidchallenge.domain.model.error.ErrorHandler
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.domain.repository.Router
import br.com.aisdigital.androidchallenge.domain.repository.interactor.AuthInteractor
import br.com.aisdigital.androidchallenge.domain.repository.interactor.LoginInteractor
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: LoginViewModel
    private val routerMock: Router = mockk(relaxed = true)
    private val authInteractorMock: AuthInteractor = mockk(relaxed = true)
    private val loginInteractorMock: LoginInteractor = mockk(relaxed = true)
    private val resourcesMock: Resources = mockk(relaxed = true)

    @Before
    fun setUp() {
        sut = LoginViewModel(routerMock, authInteractorMock, loginInteractorMock, resourcesMock)
    }

    @Test
    fun `email helper text should is null when email is valid`() {
        sut.emailInputText.value = "email@teste.com"
        val helperTextMock = "helperTextMock"

        every {
            resourcesMock.getString(any())
        } returns helperTextMock

        sut.onLoginClick()

        Assert.assertEquals(null, sut.emailHelperText.value)
    }

    @Test
    fun `email helper text should is not null when email is invalid`() {
        sut.emailInputText.value = "email@teste .com"
        val helperTextMock = "helperTextMock"

        every {
            resourcesMock.getString(any())
        } returns helperTextMock

        sut.onLoginClick()

        Assert.assertEquals(helperTextMock, sut.emailHelperText.value)
    }

    @Test
    fun `password helper text should is null when email is valid`() {
        sut.passwordInputText.value = "password"
        val helperTextMock = "helperTextMock"

        every {
            resourcesMock.getString(any())
        } returns helperTextMock

        sut.onLoginClick()

        Assert.assertEquals(null, sut.passwordHelperText.value)
    }

    @Test
    fun `password helper text should is not null when email is invalid`() {
        sut.passwordInputText.value = ""
        val helperTextMock = "helperTextMock"

        every {
            resourcesMock.getString(any())
        } returns helperTextMock

        sut.onLoginClick()

        Assert.assertEquals(helperTextMock, sut.passwordHelperText.value)
    }

    @Test
    fun `should navigate to home when auth and login requests is succeed`() {
        sut.emailInputText.value = "email@teste.com"
        sut.passwordInputText.value = "password"

        val authInteractorMock = getAuthInteractor()
        val loginInteractorMock = getLoginInteractor()
        val authExpectedResponse: AuthResponse = mockk(relaxed = true)
        val loginExpectedResponse: LoginResponse = mockk(relaxed = true)

        sut = LoginViewModel(routerMock, authInteractorMock, loginInteractorMock, resourcesMock)

        sut.requestState.observeForever { }

        sut.onLoginClick()

        authInteractorMock.requestState.value = RequestState.Success(authExpectedResponse)
        loginInteractorMock.requestState.value = RequestState.Success(loginExpectedResponse)

        verify {
            routerMock.navigateToHome(loginExpectedResponse)
        }

        sut.requestState.removeObserver { }
    }

    private fun getAuthInteractor(): AuthInteractor {
        val coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        val errorHandlerMock: ErrorHandler = mockk(relaxed = true)
        return AuthInteractor(mockk(), coroutineScope, errorHandlerMock)
    }

    private fun getLoginInteractor(): LoginInteractor {
        val coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        val errorHandlerMock: ErrorHandler = mockk(relaxed = true)
        return LoginInteractor(mockk(), coroutineScope, errorHandlerMock)
    }

}