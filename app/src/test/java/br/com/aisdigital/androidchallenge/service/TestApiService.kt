package br.com.aisdigital.androidchallenge.service

import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.Token
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestApiService {

    @get:Rule
    val mockTeamsWebServer = MockWebServer()
    @get:Rule
    val mockAuthWebServer = MockWebServer()
    @get:Rule
    val mockUserWebServer = MockWebServer()

    lateinit var teams : List<Team>

    private val testTeamsJson = """[{
        "name": "Celtics",
        "city": "New Jersey",
        "conference": "EAST",
        "teamImageUrl": "https://upload.wikimedia.org/wikipedia/pt/thumb/f/f5/Boston_Celtics.png/200px-Boston_Celtics.png",
        "description": "O Boston Celtics é uma franquia de basquetebol filiada à National Basketball Association e situada na
          cidade de Boston, no estado americano de Massachusetts. Fundado em 6 de junho de 1946, é uma das únicas equipes que se
          mantém desde que foi criada."
      }]"""

    private val testTokenJson = """{ "token" : "jkrdvjnksdv89qefn8fui31ncdn8" }"""

    private val testUserJson = """{ "name": "Jon Doe", "age": "20", "gender": "MALE" }"""

    private fun  getAPI(server : MockWebServer) : ApiService {
        return Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Before
    fun init(){
        mockTeamsWebServer.enqueue(
            MockResponse()
                .setBody(testTeamsJson)
                .setResponseCode(200))

        mockAuthWebServer.enqueue(
            MockResponse()
                .setBody(testTokenJson)
                .setResponseCode(200))

        mockUserWebServer.enqueue(
            MockResponse()
                .setBody(testUserJson)
                .setResponseCode(200))

        teams = Gson().fromJson(testTeamsJson, Array<Team>::class.java).toMutableList()
    }

    @Test
    fun testApiReturnCeltics(){

        val testObserver = getAPI(mockTeamsWebServer).getTeams().test()
        testObserver.assertValue(teams)
    }

    @Test
    fun testTeamsRoute(){
        val testObserver = getAPI(mockTeamsWebServer).getTeams().test()
        testObserver.assertNoErrors()
        assertEquals(
            "/teams",
            mockTeamsWebServer.takeRequest().path
        )
    }

    @Test
    fun testToken(){
        val testObserver = getAPI(mockAuthWebServer).auth("marcos@gmail.com", "12345").test()
        testObserver.assertNoErrors()
        assertEquals(
            "/auth?user_email%20=marcos%40gmail.com&user_password=12345",
            mockAuthWebServer.takeRequest().path
        )
    }

    @Test
    fun testUser(){
        val testObserver = getAPI(mockAuthWebServer).login( "jkrdvjnksdv89qefn8fui31ncdn8").test()
        testObserver.assertNoErrors()
        assertEquals(
            "/login",
            mockAuthWebServer.takeRequest().path
        )
    }
}