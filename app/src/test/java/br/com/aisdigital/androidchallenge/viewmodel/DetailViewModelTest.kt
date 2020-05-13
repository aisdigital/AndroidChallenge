package br.com.aisdigital.androidchallenge.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import br.com.aisdigital.androidchallenge.model.Team
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailViewModelTest {
    private lateinit var sut: DetailViewModel
    private val resourcesMock = (ApplicationProvider.getApplicationContext() as Context).resources
    private val teamMock = Team(
        "Grêmio",
        "Porto Alegre",
        "Sul",
        "",
        "O maior do Brasil"
    )

    @Before
    fun setup() {
        sut = DetailViewModel(
            resourcesMock,
            teamMock
        )
    }

    @Test
    fun `when I search for the city, it must be formatted`() {
        sut.run {
            assertEquals("Cidade: Porto Alegre", getCity())
        }
    }

    @Test
    fun `when I search for the conference, it must be formatted`() {
        sut.run {
            assertEquals("Conferência: Sul", getConference())
        }
    }

}