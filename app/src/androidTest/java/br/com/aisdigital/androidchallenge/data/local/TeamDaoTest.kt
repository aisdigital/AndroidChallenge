package br.com.aisdigital.androidchallenge.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.aisdigital.androidchallenge.MainCoroutineRuleAndroidTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TeamDaoTest {

    private lateinit var database: AndroidChallengeDatabase
    private lateinit var teamDao: TeamDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRuleAndroidTest()

    @Before
    fun createDb() = runBlocking {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AndroidChallengeDatabase::class.java,
        ).build()
        teamDao = database.teamDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testSaveAndLoadTeams() = runBlocking {
        val t1 = TeamDatabaseEntity(
            "Celtics",
            "New Jersey",
            "EAST",
            "",
            "O Boston Celtics é uma franquia de basquetebol filiada à National Basketball Association"
        )
        val t2 = TeamDatabaseEntity(
            "Nets",
            "New Jersey",
            "EAST",
            "",
            "O Brooklyn Nets é um time de basquete profissional americano baseado no bairro de Brooklyn"
        )
        val l = listOf(t1, t2)
        teamDao.save(l)
        val teams = teamDao.load().take(1).toList()[0]
        assertThat(teams).isEqualTo(l)
    }
}