package br.com.aisdigital.androidchallenge.login


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.ViewState
import br.com.aisdigital.androidchallenge.domain.error.AuthenticationFail
import br.com.aisdigital.androidchallenge.domain.login.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var SUT: LoginViewModel
    private lateinit var usecase:AuthenticateUser
    private val authService: AuthService = mock()
    private val loginDataSource:LoginDataSource = mock()
    private val loginValidator = LoginValidator()

    private val validLogin = Login("email@domain.com", "1234", UUID.randomUUID().toString())

    @Before fun setup() {
        usecase = AuthenticateUser(authService, loginValidator)
        SUT = LoginViewModel(usecase = usecase, loginValidator = loginValidator, dataSource = loginDataSource, ioDispatcher = testCoroutineDispatcher)
        SUT.emailAddress = validLogin.email
        SUT.userPassword = validLogin.password
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `authenticate should return a valid login when given valid email and password`() {
        testCoroutineDispatcher.runBlockingTest {

            whenever(authService.authenticate(validLogin.copy(token = ""))).thenReturn(validLogin)

            testCoroutineDispatcher.pauseDispatcher()
            SUT.authenticate()

            val loading = SUT.loginState.getOrAwaitValue()

            assertThat(loading).isEqualTo(ViewState.Loading)

            testCoroutineDispatcher.resumeDispatcher()

            val result = SUT.loginState.getOrAwaitValue()
            assertThat(result).isEqualTo(ViewState.Success(validLogin))
        }
    }

    @Test fun `authenticate should throw a AuthenticationFail Exception when given invalid login`() {
        testCoroutineDispatcher.runBlockingTest {

            whenever(authService.authenticate(validLogin.copy(token = ""))).thenReturn(validLogin.copy(token = ""))

            testCoroutineDispatcher.pauseDispatcher()
            SUT.authenticate()

            val loading = SUT.loginState.getOrAwaitValue()

            assertThat(loading).isEqualTo(ViewState.Loading)

            testCoroutineDispatcher.resumeDispatcher()

            val result = SUT.loginState.getOrAwaitValue()
            assertThat(result).isEqualTo(ViewState.Failed(AuthenticationFail))
        }
    }
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}