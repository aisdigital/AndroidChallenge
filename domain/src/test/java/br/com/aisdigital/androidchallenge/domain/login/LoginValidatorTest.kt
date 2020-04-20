package br.com.aisdigital.androidchallenge.domain.login

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Before
import org.junit.Test

class LoginValidatorTest {

    private lateinit var SUT: LoginValidator
    private val login = Login(
        "derlandy.belchior@gmail.com",
        "123456",
        "accef952-37e5-4145-8839-5693480ec8ac"
    )

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

    @Test fun `isAuthenticated should return true when login is valid and token is not empty` () {

        //When
        val result = SUT.isAuthenticated(login)

        //Then
        assertThat(result).isTrue()
    }

    @Test fun `isAuthenticated should return false when login is valid and token is empty` () {
        //Given

        val entry = login.copy(token = "")

        //When
        val result = SUT.isAuthenticated(entry)

        //Then
        assertThat(result).isFalse()
    }

    @Test fun `isAuthenticated should return false when login is not valid and token is empty` () {
        //Given
        val entry = login.copy("derlandy", "", "")

        //When
        val result = SUT.isAuthenticated(entry)

        //Then
        assertThat(result).isFalse()
    }

    @Test fun `isAuthenticated should return false when login is not valid and token is not empty` () {
        //Given
        val entry = login.copy("derlandy", "")

        //When
        val result = SUT.isAuthenticated(entry)

        //Then
        assertThat(result).isFalse()
    }
}