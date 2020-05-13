package br.com.aisdigital.androidchallenge.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.model.UserInfo
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProfileViewModelTest {

    private lateinit var sut: ProfileViewModel
    private val resourcesMock = (ApplicationProvider.getApplicationContext() as Context).resources
    private val userInfoMock = UserInfo(
        "Renato Portaluppi",
        "56",
        "MALE"
    )

    @Before
    fun setup() {
        sut = ProfileViewModel(
            resourcesMock,
            userInfoMock
        )
    }

    @Test
    fun `when I look for age it must return formatted`() {
        sut.run {
            assertEquals("56 anos", getAge())
        }
    }

    @Test
    fun `when the user is male then he must return the male avatar`() {
        sut.run {
            assertEquals(R.drawable.avatar_male, getAvatar())
        }
    }

    @Test
    fun `when the user is female then the female avatar must be returned`() {
        sut.run {
            userInfo.gender = "FEMALE"

            assertEquals(R.drawable.avatar_female, getAvatar())
        }
    }

}