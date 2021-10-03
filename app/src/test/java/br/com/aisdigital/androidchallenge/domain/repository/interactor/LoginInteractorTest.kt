package br.com.aisdigital.androidchallenge.domain.repository.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.model.error.ErrorHandler
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.domain.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginInteractorTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: LoginInteractor
    private val repositoryMock: Repository = mockk(relaxed = true)
    private val coroutineScope = CoroutineScope(Dispatchers.Unconfined)
    private val errorHandlerMock: ErrorHandler = mockk(relaxed = true)

    private val tokenMock = "tokenMock"

    @Before
    fun setUp() {
        sut = LoginInteractor(repositoryMock, coroutineScope, errorHandlerMock)
    }

    @Test
    fun `setup IDLE status on init`() {
        Assert.assertEquals(RequestState.Idle, sut.requestState.value)
    }

    @Test
    fun `update request state to ERROR when do login has failed`() {
        coEvery {
            repositoryMock.doLogin(tokenMock)
        } throws Exception()

        sut.doLogin(tokenMock)

        Assert.assertTrue(sut.requestState.value is RequestState.Error)
    }

    @Test
    fun `update request state to SUCCESS when do login has succeed`() {
        val expectedResponse: LoginResponse = mockk(relaxed = true)

        coEvery {
            repositoryMock.doLogin(tokenMock)
        } returns expectedResponse

        sut.doLogin(tokenMock)

        Assert.assertEquals(RequestState.Success(expectedResponse), sut.requestState.value)
    }

}