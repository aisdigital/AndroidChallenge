package br.com.aisdigital.androidchallenge.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.aisdigital.androidchallenge.MainCoroutineRuleTest
import br.com.aisdigital.androidchallenge.Util.getListOfTeams
import br.com.aisdigital.androidchallenge.Util.getUser
import br.com.aisdigital.androidchallenge.entities.Team
import br.com.aisdigital.androidchallenge.entities.User
import br.com.aisdigital.androidchallenge.getOrAwaitValueTest
import br.com.aisdigital.androidchallenge.repositories.LoginRepository
import br.com.aisdigital.androidchallenge.repositories.TeamRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRuleTest()

    @Mock
    private lateinit var repository: TeamRepository
    @Mock
    private lateinit var loginRepository: LoginRepository
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var teams: List<Team>
    private lateinit var user: User

    @Before
    fun setup() = runBlocking {
        teams = getListOfTeams(10)
        user = getUser()
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getTeams()).thenAnswer {
            return@thenAnswer flow {
                emit(teams)
            }
        }
        Mockito.`when`(loginRepository.getLoggedUser()).thenAnswer {
            return@thenAnswer flow {
                emit(user)
            }
        }

        homeViewModel = HomeViewModel(repository, loginRepository)
    }

    @Test
    fun testViewModelInit() {
        Truth.assertThat(homeViewModel.teams.getOrAwaitValueTest()).isEqualTo(teams)
        Truth.assertThat(homeViewModel.user.getOrAwaitValueTest()).isEqualTo(user)
    }
}