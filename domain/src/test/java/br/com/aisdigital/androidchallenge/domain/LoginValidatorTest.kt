package br.com.aisdigital.androidchallenge.domain

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Before
import org.junit.Test

class LoginValidatorTest {

    private lateinit var SUT: LoginValidator
    private val login = Login("derlandy.belchior@gmail.com", "123456")

    @Before
    fun setup() {
        SUT = LoginValidator()
    }

    @Test fun `isValid should return false when email has a invalid value` () {
        //Given
        val entry = login.copy("derlandy")

        //When
        val result = SUT.isValid(entry)

        //Then
        assertThat(result).isFalse()
    }

    @Test fun `isValid should return false when password is empty` () {
        //Given
        val entry = login.copy(password = "")

        //When
        val result = SUT.isValid(entry)

        //Then
        assertThat(result).isFalse()
    }

    @Test fun `isValid should return false when email is not valid and password is empty` () {
        //Given
        val entry = login.copy(email = "derlandy", password = "")

        //When
        val result = SUT.isValid(entry)

        //Then
        assertThat(result).isFalse()
    }

    @Test fun `isValid should return true when email has a valid value` () {

        //When
        val result = SUT.isValid(login)

        //Then
        assertThat(result).isTrue()
    }
}