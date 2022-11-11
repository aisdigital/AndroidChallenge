package br.com.aisdigital.androidchallenge.domain

import br.com.aisdigital.androidchallenge.data.LoginRepository
import br.com.aisdigital.androidchallenge.data.mock.MockLocalDatasource
import br.com.aisdigital.androidchallenge.data.mock.MockRemoteDatasource
import br.com.aisdigital.androidchallenge.data.model.ResultApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AuthenticateUsecaseTest {

    private lateinit var authenticateUsecase: AuthenticateUsecase

    private fun setupMocks(apiSuccess: Boolean) {
        val mockRemoteDatasource = MockRemoteDatasource(success = apiSuccess)
        val sharedPreferencesLocalDatasource = MockLocalDatasource()
        val loginRepository =
            LoginRepository(mockRemoteDatasource, sharedPreferencesLocalDatasource)
        authenticateUsecase = AuthenticateUsecase(loginRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test authenticate usecase SUCCESS`() {
        runTest {
            setupMocks(apiSuccess = true)
            val result = authenticateUsecase.authenticate("teste@teste.com", "hsahdshdha")
            assertTrue(result is ResultApi.Success)
            assertEquals("12345678", result.data.token)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test authenticate usecase ERROR`() {
        runTest {
            setupMocks(apiSuccess = false)
            val result = authenticateUsecase.authenticate("teste@teste.com", "hsahdshdha")
            assertTrue(result is ResultApi.Error)
            assertEquals("error", result.errorMessage)
        }
    }

}