package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.model.User
import br.com.aisdigital.androidchallenge.modules.Module
import br.com.aisdigital.androidchallenge.networking.TeamsApi
import br.com.aisdigital.androidchallenge.persistence.dao.UserDao
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class TestLoginRepository {

    private val userDao: UserDao = mock()
    private val teamsApi: TeamsApi = mock()
    private val schedulerProvider: Module.SchedulerProvider = mock()

    private lateinit var loginRepository: LoginRepository

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun testLoginSuccess() {
        loginRepository = LoginRepository(userDao, teamsApi, schedulerProvider)

        val user: User = User("Rodrigo", 35, "male")

        whenever(loginRepository.login("test@email.com", "123456"))
            .thenReturn(Single.just(user))

        val tester = loginRepository.login("test@email.com", "123456").test()
        tester.assertValue(user)
    }
}