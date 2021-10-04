package br.com.aisdigital.androidchallenge.viewmodel.home

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.aisdigital.androidchallenge.domain.model.RequestState
import br.com.aisdigital.androidchallenge.domain.model.error.ErrorHandler
import br.com.aisdigital.androidchallenge.domain.model.login.LoginResponse
import br.com.aisdigital.androidchallenge.domain.model.teams.TeamsResponse
import br.com.aisdigital.androidchallenge.domain.repository.interactor.TeamsInteractor
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: HomeViewModel
    private val teamsInteractorMock: TeamsInteractor = mockk(relaxed = true)
    private val resourcesMock: Resources = mockk(relaxed = true)

    @Before
    fun setUp() {
        sut = HomeViewModel(teamsInteractorMock, resourcesMock)
    }

    @Test
    fun `get teams list on resume`() {
        sut.onResume()

        verify {
            teamsInteractorMock.getTeams()
        }
    }

    @Test
    fun `get teams list on retry`() {
        sut.onRetry()

        verify {
            teamsInteractorMock.getTeams()
        }
    }

    @Test
    fun `should set team list when get teams request is success`() {
        val teamsInteractorMock = getTeamsInteractor()
        val expectedResponse: List<TeamsResponse> = mockk(relaxed = true)

        sut = HomeViewModel(teamsInteractorMock, resourcesMock)

        sut.requestState.observeForever { }
        sut.teamsList.observeForever { }

        teamsInteractorMock.getTeams()

        teamsInteractorMock.requestState.value = RequestState.Success(expectedResponse)

        Assert.assertEquals(expectedResponse, sut.teamsList.value)

        sut.requestState.removeObserver { }
        sut.teamsList.removeObserver { }
    }

    @Test
    fun `should set user name on load`() {
        val loginResponseMockk: LoginResponse = mockk(relaxed = true)
        val userNameMock = "userNameMock"

        every {
            resourcesMock.getString(
                any(),
                any()
            )
        } returns userNameMock

        sut.load(loginResponseMockk)

        Assert.assertEquals(userNameMock, sut.userName.value)
    }

    @Test
    fun `should set user age on load`() {
        val loginResponseMockk: LoginResponse = mockk(relaxed = true)
        val userAgeMock = "userAgeMock"

        every {
            resourcesMock.getString(
                any(),
                any()
            )
        } returns userAgeMock

        sut.load(loginResponseMockk)

        Assert.assertEquals(userAgeMock, sut.userAge.value)
    }

    @Test
    fun `should set user gender on load`() {
        val loginResponseMockk: LoginResponse = mockk(relaxed = true)
        val userGenderMock = "userGenderMock"

        every {
            resourcesMock.getString(
                any(),
                any()
            )
        } returns userGenderMock

        sut.load(loginResponseMockk)

        Assert.assertEquals(userGenderMock, sut.userGender.value)
    }

    private fun getTeamsInteractor(): TeamsInteractor {
        val coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        val errorHandlerMock: ErrorHandler = mockk(relaxed = true)
        return TeamsInteractor(mockk(), coroutineScope, errorHandlerMock)
    }

}