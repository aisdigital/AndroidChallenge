package br.com.aisdigital.androidchallenge.networking

import br.com.aisdigital.androidchallenge.domain.error.NoTeamsFound
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class FetchTeamsNetworkingServiceTest {

    private lateinit var SUT: FetchTeamsNetworkingService
    private lateinit var mockWebServer: MockWebServer
    private lateinit var restApi: TeamsIO

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
            .create(TeamsIO::class.java
            )

        SUT = FetchTeamsNetworkingService(restApi)
    }

    @After
    fun `after each test` () {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetchAll should return a list of Team`() {
        //Scenario
        `server should response with a list of teams`()

        runBlocking {

            //When
            val response = SUT.fetchAll()

            //Then
            Assertions.assertThat(response).isNotEmpty()
        }
    }



    @Test
    fun `fetchAll should throw a NoTeamsFound exception when retrieve a empty list`() {
        //Scenario
        `server should response with a empty list`()

        val error: Result<*> = runCatching {
            return runBlocking {
                val response = SUT.fetchAll()
            }
        }

        Assertions.assertThat(error.exceptionOrNull()).isEqualTo(NoTeamsFound)
    }

    private fun `server should response with a list of teams` () {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setHeader("Content-type", "application/json")
            .setBody(
                """
                [
                    {
                        "name": "Celtics",
                        "city": "New Jersey",
                        "conference": "EAST",
                        "teamImageUrl": "https://upload.wikimedia.org/wikipedia/pt/thumb/f/f5/Boston_Celtics.png/200px-Boston_Celtics.png",
                        "description": "O Boston Celtics é uma franquia de basquetebol filiada à National Basketball Association e situada na cidade de Boston, no estado americano de Massachusetts. Fundado em 6 de junho de 1946, é uma das únicas equipes que se mantém desde que foi criada."
                    },
                    {
                        "name": "Nets",
                        "city": "New Jersey",
                        "conference": "EAST",
                        "teamImageUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Brooklyn_Nets_newlogo.svg/200px-Brooklyn_Nets_newlogo.svg.png",
                        "description": "O Brooklyn Nets é um time de basquete profissional americano baseado no bairro de Brooklyn, em Nova York. Os Nets competem na National Basketball Association como membro da Divisão Atlântica da Conferência Leste. A equipe joga seus jogos em casa no Barclays Center"
                    }
                ]
                """
            )
        )
    }

    private fun `server should response with a empty list` () {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setHeader("Content-type", "application/json")
            .setBody(
                """
                []
                """
            )
        )
    }
}