package br.com.aisdigital.androidchallenge.viewmodel

import br.com.aisdigital.androidchallenge.BaseTest
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.helpers.FakeHttpException
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TeamsViewModelTest : BaseTest() {

    lateinit var teamsViewModel: TeamsViewModel

    @Before
    override fun setUp() {
        super.setUp()

        teamsViewModel = TeamsViewModel(app)
    }

    @Test
    fun `Testar os dados do teams`() = runBlocking {

        teamsViewModel.getTeams()

        teamsViewModel.success.observeForever {
            // empty
            assertTrue(it.isNotEmpty())

            // size
            assertEquals(6, it.size)

            // index 0
            assertEquals("Celtics", it[0].name)
            assertEquals("New Jersey", it[0].city)
            assertEquals("EAST", it[0].conference)
            assertEquals("https://upload.wikimedia.org/wikipedia/pt/thumb/f/f5/Boston_Celtics.png/200px-Boston_Celtics.png", it[0].teamImageUrl)
            assertEquals("O Boston Celtics é uma franquia de basquetebol filiada à National Basketball Association e situada na cidade de Boston, no estado americano de Massachusetts. Fundado em 6 de junho de 1946, é uma das únicas equipes que se mantém desde que foi criada.", it[0].description)
        }
    }

    @Test
    fun `Testar erro do teams`() = runBlocking {

        // Configurar Mockito
        Mockito.`when`(teamsViewModel.backendRepository.getTeamsAsync()).thenThrow(
            FakeHttpException()
        )
        Mockito.`when`(teamsViewModel.resources.getString(any())).thenReturn(context.getString(R.string.msg_erro_http))

        teamsViewModel.getTeams()

        teamsViewModel.error.observeForever {
            assertEquals(context.getString(R.string.msg_erro_http), it)
        }
    }
}
