package br.com.aisdigital.androidchallenge.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.data.*
import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.LoginResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi
import br.com.aisdigital.androidchallenge.domain.AuthenticateUsecase
import br.com.aisdigital.androidchallenge.domain.LoginUsecase
import br.com.aisdigital.androidchallenge.domain.ValidateEmailUsecase
import br.com.aisdigital.androidchallenge.helper.CoroutineDispatcherRule
import br.com.aisdigital.androidchallenge.helper.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ExampleUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesDispatcherRule = CoroutineDispatcherRule()

    private lateinit var viewModel: LoginViewModel

    private fun setupMocks(apiSuccess: Boolean = false) {
        val mockRemoteDatasource = MockRemoteDatasource()
        mockRemoteDatasource.success = apiSuccess
        val sharedPreferencesLocalDatasource = MockLocalDatasource()
        val loginRepository =
            LoginRepository(mockRemoteDatasource, sharedPreferencesLocalDatasource)
        val authenticateUsecase = AuthenticateUsecase(loginRepository)
        val loginUsecase = LoginUsecase(loginRepository)
        val validateEmailUsecase = ValidateEmailUsecase()
        viewModel = LoginViewModel(loginUsecase, authenticateUsecase, validateEmailUsecase)
    }

    @Test
    fun `test livedata emitting ONLY email validation error state`() {
        setupMocks()
        viewModel.authenticate("teste@teste.coj ", "tedassd")

        viewModel.validationErrorStateLiveData.observeOnce { validationErrorState ->
            assertNotNull(validationErrorState)
            assertEquals(
                validationErrorState.emailValidationErrorResourceId,
                R.string.email_validation_error
            )
            assertEquals(
                validationErrorState.passwordValidationErrorResourceId,
                R.string.empty
            )
        }
    }

    @Test
    fun `test livedata emitting ONLY password validation error state`() {
        setupMocks()
        viewModel.authenticate("teste@teste.com", "")

        viewModel.validationErrorStateLiveData.observeOnce { validationErrorState ->
            assertNotNull(validationErrorState)
            assertEquals(
                validationErrorState.passwordValidationErrorResourceId,
                R.string.password_validation_error
            )
            assertEquals(
                validationErrorState.emailValidationErrorResourceId,
                R.string.empty
            )
        }
    }

    @Test
    fun `test livedata emitting BOTH password and e-mail validation error state`() {
        setupMocks()
        viewModel.authenticate("teste@teste.com  ", "")

        viewModel.validationErrorStateLiveData.observeOnce { validationErrorState ->
            assertNotNull(validationErrorState)
            assertEquals(
                validationErrorState.passwordValidationErrorResourceId,
                R.string.password_validation_error
            )
            assertEquals(
                validationErrorState.emailValidationErrorResourceId,
                R.string.email_validation_error
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test livedata emitting loading state`() = runTest {
        setupMocks(apiSuccess = true)
        viewModel.setLoadingState(true)
        viewModel.loadingStateLiveData.observeOnce {
            assertTrue(it.isLoading)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test api result SUCCESS`() {
        runTest {
            setupMocks(apiSuccess = true)
            viewModel.authenticate("teste@teste.com", "sdsddsds")

            viewModel.loginResultStateLiveData.observeOnce {
                assertTrue(it.success)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test api result ERROR`() {
        runTest {
            setupMocks(apiSuccess = false)
            viewModel.authenticate("teste@teste.com", "sdsddsds")
            viewModel.loginResultStateLiveData.observeOnce {
                assertFalse(it.success)
            }
        }
    }

}

class MockRemoteDatasource : ILoginRemoteDatasource {

    var success: Boolean = false

    override suspend fun authenticate(
        email: String,
        password: String
    ): ResultApi<AuthenticationResponse> {
        return if (success) {
            ResultApi.Success(AuthenticationResponse("87239813981"))
        } else {
            ResultApi.Error("error")
        }
    }

    override suspend fun login(authToken: String): ResultApi<LoginResponse> {
        return if (success) {
            ResultApi.Success(LoginResponse("Carlos", age = "31", gender = "Male"))
        } else {
            ResultApi.Error("error")
        }
    }

}

class MockLocalDatasource : ISharedPreferencesLocalDatasource {
    override fun saveString(key: String, value: String) {
    }

    override fun getString(key: String): String {
        return "";
    }

}




