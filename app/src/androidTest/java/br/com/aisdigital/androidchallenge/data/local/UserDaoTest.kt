package br.com.aisdigital.androidchallenge.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.aisdigital.androidchallenge.MainCoroutineRuleAndroidTest
import br.com.aisdigital.androidchallenge.UtilAndroidTest.getDatabaseUser
import com.google.common.truth.Truth
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
class UserDaoTest {
    private lateinit var database: AndroidChallengeDatabase
    private lateinit var userDao: UserDao

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
        userDao = database.userDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testSaveAndLoadUser() = runBlocking {
        val user = getDatabaseUser()
        userDao.save(user)
        val userDb = userDao.load().take(1).toList()[0]
        Truth.assertThat(user).isEqualTo(userDb)
    }

    @Test
    fun testDelete() = runBlocking {
        val user = getDatabaseUser()
        userDao.save(user)
        userDao.delete()
        val userDb = userDao.load().take(1).toList()[0]
        Truth.assertThat(userDb).isNull()
    }
}