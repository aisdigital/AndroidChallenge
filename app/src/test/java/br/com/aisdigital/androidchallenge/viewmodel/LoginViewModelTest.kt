package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import br.com.aisdigital.androidchallenge.domain.repository.AndroidChallengeRepository
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {

    private val repository: AndroidChallengeRepository = mockk(relaxed = true)
    private lateinit var sut: LoginViewModel

    @Before
    fun setup() {
        sut = LoginViewModel(repository)
    }

    @Test
    fun `when email is empty invalid data message must be shown`() {
        sut.run {
            val password = "teste123"
            val email = ""
            validateData(email, password)

            Assert.assertEquals(View.VISIBLE, invalidDataVisibility.value)
        }
    }

    @Test
    fun `when password is empty invalid data message must be shown`() {
        sut.run {
            val password = ""
            val email = "teste@teste.com"
            validateData(email, password)

            Assert.assertEquals(View.VISIBLE, invalidDataVisibility.value)
        }
    }

    @Test
    fun `when password and email arent empty invalid data message must be hidden`() {
        sut.run {
            val password = "teste1234"
            val email = "teste@teste.com"
            validateData(email, password)

            Assert.assertEquals(View.GONE, invalidDataVisibility.value)
        }
    }
}