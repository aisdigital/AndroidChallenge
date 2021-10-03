package br.com.aisdigital.androidchallenge.domain.repository.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.model.error.ErrorHandler
import br.com.aisdigital.androidchallenge.domain.model.teams.TeamsResponse
import br.com.aisdigital.androidchallenge.domain.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TeamsInteractorTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: TeamsInteractor
    private val repositoryMock: Repository = mockk(relaxed = true)
    private val coroutineScope = CoroutineScope(Dispatchers.Unconfined)
    private val errorHandlerMock: ErrorHandler = mockk(relaxed = true)

    @Before
    fun setUp() {
        sut = TeamsInteractor(repositoryMock, coroutineScope, errorHandlerMock)
    }

    @Test
    fun `setup IDLE status on init`() {
        Assert.assertEquals(RequestState.Idle, sut.requestState.value)
    }

    @Test
    fun `update request state to ERROR when get teams has failed`() {
        coEvery {
            repositoryMock.getTeams()
        } throws Exception()

        sut.getTeams()

        Assert.assertTrue(sut.requestState.value is RequestState.Error)
    }

    @Test
    fun `update request state to SUCCESS when get teams has succeed`() {
        val expectedResponse: List<TeamsResponse> = mockk(relaxed = true)

        coEvery {
            repositoryMock.getTeams()
        } returns expectedResponse

        sut.getTeams()

        Assert.assertEquals(RequestState.Success(expectedResponse), sut.requestState.value)
    }

}