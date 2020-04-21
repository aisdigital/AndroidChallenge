package br.com.aisdigital.androidchallenge.networking

import br.com.aisdigital.androidchallenge.domain.login.Login
import br.com.aisdigital.androidchallenge.domain.user.Gender
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class FetchUserNetworkingServiceTest {

    private lateinit var SUT: FetchUserNetworkingService
    private lateinit var mockWebServer: MockWebServer
    private lateinit var restApi: UserIO

    @Before
    fun before_each_test() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        restApi = RetrofitBuilder(
            mockWebServer.url("/").toString(),
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()
            )
            .create(UserIO::class.java
        )

        SUT = FetchUserNetworkingService(restApi)
    }

    @After
    fun `after each test` () {
        mockWebServer.shutdown()
    }

    @Test
    fun `authenticate with a valid Login should return a login with a valid token`() {
        //Given
        val entry = Login("derlandy.belchior@gmail.com", "123456")

        `server should response with a valid token`()

        runBlocking {

            //When
            val response = SUT.authenticate(entry)

            //Then
            assertThat(response.token).isNotEmpty()
        }
    }

    @Test
    fun `login with a auhtenticated Login should return a User`() {
        //Given
        val entry = Login("derlandy.belchior@gmail.com", "123456", "222df214-b292-46a8-a359-93ce09b4c88d")

        `server should response with a valid user when login is valid`()

        runBlocking {

            //When
            val response = SUT.login(entry)

            //Then
            assertThat(response.name).isNotEmpty()
            assertThat(response.age).isGreaterThan(0)
            assertThat(response.gender).isIn(Gender.MALE, Gender.FEMALE)
        }
    }

    private fun `server should response with a valid token` () {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setHeader("Content-type", "application/json")
            .setBody(
                """
                    {
                      "token": "222df214-b292-46a8-a359-93ce09b4c88d"
                    }
                """
            )
        )
    }

    private fun `server should response with a valid user when login is valid` () {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setHeader("Content-type", "application/json")
                .setBody(
                    """
                        {
                            "name": "Jon Doe",
                            "age": "20",
                            "gender": "FEMALE"
                        }
                    """
                )
        )
    }
}