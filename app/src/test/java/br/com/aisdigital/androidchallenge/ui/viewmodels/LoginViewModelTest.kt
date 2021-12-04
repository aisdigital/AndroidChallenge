package br.com.aisdigital.androidchallenge.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.aisdigital.androidchallenge.MainCoroutineRuleTest
import br.com.aisdigital.androidchallenge.Util
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
class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRuleTest()

    @Mock
    private lateinit var repository: LoginRepository
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user: User

    @Before
    fun setup() = runBlocking {
        user = Util.getUser()
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getLoggedUser()).thenAnswer {
            return@thenAnswer flow {
                emit(user)
            }
        }
        Mockito.`when`(repository.login("jon_doe2@example.com", "12345678")).then {
            user = Util.getUser()
            user.email = "jon_doe2@example.com"
            true
        }

        loginViewModel = LoginViewModel(repository)
    }

    @Test
    fun testLoggedUser() {
        Truth.assertThat(loginViewModel.loginUser.getOrAwaitValueTest()).isEqualTo(user)
    }

    @Test
    fun testLoginSuccess() {
        loginViewModel.login("jon_doe2@example.com", "12345678")
        Truth.assertThat(user.email).isEqualTo("jon_doe2@example.com")
    }
}