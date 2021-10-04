package br.com.aisdigital.androidchallenge.domain.repository.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.model.auth.AuthResponse
import br.com.aisdigital.androidchallenge.domain.model.error.ErrorHandler
import br.com.aisdigital.androidchallenge.domain.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthInteractorTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: AuthInteractor
    private val repositoryMock: Repository = mockk(relaxed = true)
    private val coroutineScope = CoroutineScope(Dispatchers.Unconfined)
    private val errorHandlerMock: ErrorHandler = mockk(relaxed = true)

    private val emailMock = "emailMock"
    private val passwordMock = "passwordMock"

    @Before
    fun setUp() {
        sut = AuthInteractor(repositoryMock, coroutineScope, errorHandlerMock)
    }

    @Test
    fun `setup IDLE status on init`() {
        assertEquals(RequestState.Idle, sut.requestState.value)
    }

    @Test
    fun `update request state to ERROR when do authenticate has failed`() {
        coEvery {
            repositoryMock.doAuthenticate(emailMock, passwordMock)
        } throws Exception()

        sut.doAuth(emailMock, passwordMock)

        assertTrue(sut.requestState.value is RequestState.Error)
    }

    @Test
    fun `update request state to SUCCESS when do authenticate has succeed`() {
        val expectedResponse: AuthResponse = mockk(relaxed = true)

        coEvery {
            repositoryMock.doAuthenticate(emailMock, passwordMock)
        } returns expectedResponse

        sut.doAuth(emailMock, passwordMock)

        assertEquals(RequestState.Success(expectedResponse), sut.requestState.value)
    }

}