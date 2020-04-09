package br.com.aisdigital.androidchallenge.viewmodel

import android.view.View
import br.com.aisdigital.androidchallenge.domain.repository.AndroidChallengeRepository
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TeamListViewModelTest {

    private val repository: AndroidChallengeRepository = mockk(relaxed = true)
    private lateinit var sut: TeamListViewModel

    @Before
    fun setup() {
        sut = TeamListViewModel(repository)
    }

    @Test
    fun `when there is no teams then I must show a message`() {
        sut.run {
            teamList.value = emptyList()
            validateData()

            Assert.assertEquals(View.VISIBLE, noPostFoundVisibility.value)
        }
    }
}