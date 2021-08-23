package br.com.aisdigital.androidchallenge.networking

import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.Token
import br.com.aisdigital.androidchallenge.model.User
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestTeamsApi {

    private val testTokenJson = """{"token": "jkrdvjnksdv89qefn8fui31ncdn8"}"""

    private val testUserJson = """{"name": "Rodrigo", "age": "35", "gender": "MALE"}"""

    private val testTeamsJson = """[{
        "name": "Celtics",
        "city": "Boston",
        "conference": "EAST",
        "teamImageUrl": "https://upload.wikimedia.org/wikipedia/pt/thumb/f/f5/Boston_Celtics.png/200px-Boston_Celtics.png",
        "description": "O Boston Celtics é uma franquia de basquetebol filiada à National Basketball Association e situada na
          cidade de Boston, no estado americano de Massachusetts. Fundado em 6 de junho de 1946, é uma das únicas equipes que se
          mantém desde que foi criada."
      }]"""

    @get:Rule
    val mockUser = MockWebServer()

    @get:Rule
    val mockToken = MockWebServer()

    @get:Rule
    val mockTeams = MockWebServer()

    @Before
    fun init() {

        mockUser.enqueue(MockResponse().setBody(testUserJson).setResponseCode(200))

        mockToken.enqueue(MockResponse().setBody(testTokenJson).setResponseCode(200))

        mockTeams.enqueue(MockResponse().setBody(testTeamsJson).setResponseCode(200))
    }

    @Test
    fun `get token with success`() {

        val token = Token(token = "jkrdvjnksdv89qefn8fui31ncdn8")

        val testObserver = getTeamsApi(mockToken)
            .makeAuth("email@teste.com", "123456").test()


        testObserver.assertNoErrors()
        testObserver.assertValue(token)
    }

    @Test
    fun `get user with success`() {
        val user = Gson().fromJson(testUserJson, User::class.java)

        val testObserver = getTeamsApi(mockUser).login( "jkrdvjnksdv89qefn8fui31ncdn8").test()

        testObserver.assertNoErrors()
        testObserver.assertValue(user)
    }

    @Test
    fun `get a list of teams`() {

        val teams = Gson().fromJson(testTeamsJson, Array<Team>::class.java).toMutableList()

        val testObserver = getTeamsApi(mockTeams).getTeams().test()

        testObserver.assertNoErrors()
        testObserver.assertValue(teams)
    }

    private fun getTeamsApi(server : MockWebServer) : TeamsApi {
        return Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TeamsApi::class.java)
    }
}