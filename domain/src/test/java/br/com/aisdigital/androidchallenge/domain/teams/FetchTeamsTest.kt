package br.com.aisdigital.androidchallenge.domain.teams

import br.com.aisdigital.androidchallenge.domain.error.NoTeamsFound
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.fail
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchTeamsTest {

    private val fetchTeamService = mock<FetchTeamService>()
    private lateinit var usecase: FetchTeams

    private val teamList by lazy {
        listOf(
            Team(
                "Celtics",
                "New Jersey",
                "EAST",
                "https://upload.wikimedia.org/wikipedia/pt/thumb/f/f5/Boston_Celtics.png/200px-Boston_Celtics.png",
                "O Boston Celtics é uma franquia de basquetebol filiada à National Basketball Association e situada na cidade de Boston, no estado americano de Massachusetts. Fundado em 6 de junho de 1946, é uma das únicas equipes que se mantém desde que foi criada."
            )
        )
    }

    @Before fun setup() {
        usecase = FetchTeams(fetchTeamService)
    }

    @Test fun `listAll should retrieve a list of teams`() {

        runBlockingTest {
            whenever(fetchTeamService.fetchAll()).thenReturn(
                teamList
            )

            val result = usecase.listAll()

            assertThat(result).isEqualTo(teamList)
        }
    }

    @Test fun `listAll should throw NoTeamsFound exception with empty result list` () {
        try {
            runBlockingTest {
                whenever(fetchTeamService.fetchAll()).thenReturn(emptyList())

                usecase.listAll()

                fail("Should have thrown NoTeamsFound Exception") as Throwable
            }
        } catch (error: Throwable) {
            Assertions.assertThat(error).isEqualTo(NoTeamsFound)
        }
    }
}