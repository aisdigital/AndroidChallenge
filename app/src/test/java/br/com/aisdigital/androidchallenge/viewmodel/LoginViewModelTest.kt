package br.com.aisdigital.androidchallenge.viewmodel

import br.com.aisdigital.androidchallenge.BaseTest
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.helpers.FakeHttpException
import br.com.aisdigital.androidchallenge.model.FieldError
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginViewModelTest : BaseTest() {

    lateinit var loginViewModel: LoginViewModel

    @Before
    override fun setUp() {
        super.setUp()

        loginViewModel = LoginViewModel(app)
    }

    @Test
    fun `Testar login com user (email) e password corretos`() = runBlocking {

        loginViewModel.setUser("raphael_amorim@outlook.com")
        loginViewModel.setPassword("R@a")
        loginViewModel.postLogin()

        loginViewModel.success.observeForever {
            assertTrue(it)
        }
    }

    @Test
    fun `Testar login com user e password vazios`() = runBlocking {

        loginViewModel.setUser("")
        loginViewModel.setPassword("")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_user, R.string.msg_user_obrigatorio)))
        assertTrue(result.contains(FieldError(R.id.til_password, R.string.msg_password_obrigatorio)))
    }

    @Test
    fun `Testar login com user (email) invalido`() = runBlocking {

        loginViewModel.setUser("teste")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_user, R.string.msg_user_email_invalido)))
    }

    @Test
    fun `Testar login com password nao contendo caracter especial`() = runBlocking {

        loginViewModel.setPassword("Ra1")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_password, R.string.msg_password_invalido)))
    }

    @Test
    fun `Testar login com password nao contendo caracter maiusculo`() = runBlocking {

        loginViewModel.setPassword("@a1")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_password, R.string.msg_password_invalido)))
    }

    @Test
    fun `Testar login com password nao contendo caracter minusculo ou numero`() = runBlocking {

        loginViewModel.setPassword("R@@R")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_password, R.string.msg_password_invalido)))
    }

    @Test
    fun `Testar erro request login auth`() = runBlocking {

        // Configurar Mockito
        Mockito.`when`(loginViewModel.backendRepository.postAuthAsync(any(), any())).thenThrow(
            FakeHttpException()
        )
        Mockito.`when`(loginViewModel.resources.getString(any())).thenReturn(context.getString(R.string.msg_erro_http))

        loginViewModel.setUser("email@email.com")
        loginViewModel.setPassword("R@!a")

        loginViewModel.postLogin()

        loginViewModel.error.observeForever {
            assertEquals(context.getString(R.string.msg_erro_http), it)
        }
    }

    @Test
    fun `Testar erro request login user`() = runBlocking {

        // Configurar Mockito
        Mockito.`when`(loginViewModel.backendRepository.getLoginAsync()).thenThrow(
            FakeHttpException()
        )
        Mockito.`when`(loginViewModel.resources.getString(any())).thenReturn(context.getString(R.string.msg_erro_http))

        loginViewModel.setUser("email@email.com")
        loginViewModel.setPassword("R@!a")

        loginViewModel.postLogin()

        loginViewModel.error.observeForever {
            assertEquals(context.getString(R.string.msg_erro_http), it)
        }
    }

}
