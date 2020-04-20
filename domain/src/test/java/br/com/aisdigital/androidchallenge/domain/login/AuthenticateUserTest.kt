package br.com.aisdigital.androidchallenge.domain.login

import br.com.aisdigital.androidchallenge.domain.error.AuthenticationFail
import br.com.aisdigital.androidchallenge.domain.user.Gender
import br.com.aisdigital.androidchallenge.domain.user.User
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AuthenticateUserTest {

    private val authService = mock<AuthService>()
    private lateinit var usecase: AuthenticateUser

    private val validLogin by lazy {
        Login(
            "derlandy.belchior@gmail.com",
            "123456",
            "accef952-37e5-4145-8839-5693480ec8ac"
        )
    }

    private val validUser by lazy {
        User(
            "Jon Doe",
            20,
            Gender.MALE
        )
    }


    @Before fun setup() {
        usecase = AuthenticateUser(authService, LoginValidator())
    }

    @Test fun `doAuthentication should return a valid authenticated login` () {
        //Given
        val login = Login("derlandy.belchior@gmail.com", "123")

        runBlockingTest {
            //When
            whenever(authService.authenticate(login)).thenReturn(
                validLogin
            )

            val response = usecase.doAuthentication(login)

            //Then
            assertThat(response).isEqualTo(validLogin)
        }
    }

    @Test fun `doAuthentication should throw a AuthenticationFail when invalid login is informed` () {

        try {
            //Given
            val login = Login("derlandy", "123")

            runBlockingTest {
                //When
                whenever(authService.authenticate(login)).thenReturn(login)

                val response = usecase.doAuthentication(login)

                //Then
                assertThat(response).isEqualTo(validLogin)
                fail("Should have thrown AuthenticationFail exception") as Throwable
            }

        }catch (error: Throwable) {
            assertThat(error).isEqualTo(AuthenticationFail)
        }
    }

    @Test fun `login should return a valid authenticated user` () {
        //Given
        val login = Login("derlandy.belchior@gmail.com", "123")

        runBlockingTest {
            //When
            whenever(authService.login(login)).thenReturn(
                validUser
            )

            val response = usecase.login(login)

            //Then
            assertThat(response).isEqualTo(validUser)
        }
    }
}