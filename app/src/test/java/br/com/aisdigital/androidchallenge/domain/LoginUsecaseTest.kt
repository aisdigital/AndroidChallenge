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

class LoginUsecaseTest {

    private lateinit var loginUsecase: LoginUsecase


    private fun setupMocks(apiSuccess: Boolean) {
        val mockRemoteDatasource = MockRemoteDatasource(success = apiSuccess)
        val sharedPreferencesLocalDatasource = MockLocalDatasource()
        sharedPreferencesLocalDatasource.string = "12345678"
        val loginRepository =
            LoginRepository(mockRemoteDatasource, sharedPreferencesLocalDatasource)
        loginUsecase = LoginUsecase(loginRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test login usecase SUCCESS`() {
        runTest {
            setupMocks(apiSuccess = true)
            val result = loginUsecase.login()
            assertTrue(result is ResultApi.Success)
            assertEquals("Carlos", result.data.name)
            assertEquals("31", result.data.age)
            assertEquals("Male", result.data.gender)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test login usecase ERROR`() {
        runTest {
            setupMocks(apiSuccess = false)
            val result = loginUsecase.login()
            assertTrue(result is ResultApi.Error)
            assertEquals("error", result.errorMessage)
        }
    }
}