package br.com.aisdigital.androidchallenge.presentation

import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.model.User
import br.com.aisdigital.androidchallenge.repository.LoginRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class TestLoginViewModel {

    private val repository: LoginRepository = mock()

    private val observer: Observer<LoginState> = mock()

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {

        loginViewModel = LoginViewModel(repository)

        loginViewModel.loginResult.observeForever(observer)
    }

    @Test
    fun testLoginWithSuccess() {
        val user = User("Rodrigo", 35, "MALE")

        whenever(repository.login("rodrigo@test.com","123456"))
            .thenReturn(Single.just(user))

        loginViewModel.login("rodrigo@test.com","123456")

        verify(observer).onChanged(LoginState.Success(user))
    }
}