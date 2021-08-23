package br.com.aisdigital.androidchallenge.persistence

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.aisdigital.androidchallenge.model.User
import br.com.aisdigital.androidchallenge.persistence.dao.UserDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MainDatabaseTest {
    private lateinit var userDao: UserDao
    private lateinit var db: MainDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MainDatabase::class.java
        ).build()
        userDao = db.getUserDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndReadUser() = runBlocking {

        val user = User("Rodrigo", 35, "MALE")

        userDao.insert(user)

        val userResult = userDao.getUser()

        MatcherAssert.assertThat(userResult, CoreMatchers.equalTo(user))
    }
}