package br.com.aisdigital.androidchallenge.service

import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.Token
import br.com.aisdigital.androidchallenge.model.User
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class TestRepository {

    private val serviceApi : ApiService = mock()
    private val repository = RepositoryImpl(serviceApi)

    @Test
    fun testRepositoryTeams(){
        val teams : List<Team> = mock()
        whenever(serviceApi.getTeams()).thenReturn(Single.just(teams))

        val testObserver = repository.getTeams().test()
        testObserver.assertValue(teams)
    }

    @Test
    fun testRepositoryToken(){
        val token : Token = mock()
        whenever(serviceApi.auth("marcos@gmail.com", "12345")).thenReturn(Single.just(token))

        val testObserver = repository.auth("marcos@gmail.com", "12345").test()
        testObserver.assertValue(token)
    }

    @Test
    fun testRepositoryLogin(){
        val user : User = mock()
        whenever(serviceApi.login("jkrdvjnksdv89qefn8fui31ncdn8")).thenReturn(Single.just(user))

        val testObserver = repository.login("jkrdvjnksdv89qefn8fui31ncdn8").test()
        testObserver.assertValue(user)
    }
}