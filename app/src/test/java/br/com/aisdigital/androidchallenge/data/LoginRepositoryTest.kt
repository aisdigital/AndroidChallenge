package br.com.aisdigital.androidchallenge.data

import br.com.aisdigital.androidchallenge.data.mock.MockLocalDatasource
import br.com.aisdigital.androidchallenge.data.mock.MockRemoteDatasource
import br.com.aisdigital.androidchallenge.data.model.AuthenticationResponse
import br.com.aisdigital.androidchallenge.data.model.ResultApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoginRepositoryTest {

    private lateinit var loginRepository: LoginRepository

    private fun setupMocks(apiSuccess: Boolean = false) {
        val mockRemoteDatasource = MockRemoteDatasource(success = apiSuccess)
        val sharedPreferencesLocalDatasource = MockLocalDatasource()
        loginRepository = LoginRepository(mockRemoteDatasource, sharedPreferencesLocalDatasource)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun setupLoginMocks(apiSuccess: Boolean = false) {
        val mockRemoteDatasource = MockRemoteDatasource(success = apiSuccess)
        runTest {
            val result = mockRemoteDatasource.authenticate("teste@teste.com", "23132")
            if (result is ResultApi.Success) {
                val sharedPreferencesLocalDatasource = MockLocalDatasource()
                sharedPreferencesLocalDatasource.string = result.data.token
                loginRepository =
                    LoginRepository(mockRemoteDatasource, sharedPreferencesLocalDatasource)
            } else if (result is ResultApi.Error) {
                val sharedPreferencesLocalDatasource = MockLocalDatasource()
                sharedPreferencesLocalDatasource.string = result.errorMessage
                loginRepository =
                    LoginRepository(mockRemoteDatasource, sharedPreferencesLocalDatasource)
            }
        }

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test authenticate repository SUCCESS`() {
        runTest {
            setupMocks(apiSuccess = true)
            val result: ResultApi<AuthenticationResponse> =
                loginRepository.authenticate("teste@teste.com", "hsahdshdha")
            assertTrue(result is ResultApi.Success)
            assertEquals("12345678", result.data.token)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test authenticate repository ERROR`() {
        runTest {
            setupMocks(apiSuccess = false)
            val result = loginRepository.authenticate("teste@teste.com", "hsahdshdha")
            assertTrue(result is ResultApi.Error)
            assertEquals("error", result.errorMessage)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test login repository SUCCESS`() {
        runTest {
            setupLoginMocks(apiSuccess = true)
            val result = loginRepository.login()
            assertTrue(result is ResultApi.Success)
            assertEquals("Carlos", result.data.name)
            assertEquals("31", result.data.age)
            assertEquals("Male", result.data.gender)

        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test login repository ERROR`() {
        runTest {
            setupLoginMocks(apiSuccess = false)
            val result = loginRepository.login()
            assertTrue(result is ResultApi.Error)
            assertEquals("error", result.errorMessage)
        }
    }
}