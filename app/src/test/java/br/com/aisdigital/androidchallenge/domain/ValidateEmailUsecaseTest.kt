package br.com.aisdigital.androidchallenge.domain

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidateEmailUsecaseTest {
    private val validateEmailUsecase = ValidateEmailUsecase()

    @Test
    fun `test validate email CORRECT email`() {
        val correctEmailList = listOf(
            "email@example.com",
            "juan.gonzales@example.com",
            "email@subdomain.example.com",
            "pedro+fernanDez@example.com",
            "email@123.123.123.123",
            "1234567890@example.com",
            "email@example-one.com",
            "_______@example.com",
            "email@example.name",
            "email@example.museum",
            "email@example.co.jp",
            "klerkken-rodrigues@example.com",
            "TEStE_2311-sdad@TEstE.COM",
        )

        for (item in correctEmailList) {
            Assert.assertTrue(validateEmailUsecase.isValidEmail(item))
        }
    }

    @Test
    fun `test validate email INCORRECT email`() {
        val incorrectEmailList = listOf(
            "plainaddress",
            "#@%^%#\$@#\$@#.com",
            "@example.com",
            "あいうえお@example.com",
            "drive.google.com"
        )

        for (item in incorrectEmailList) {
            Assert.assertFalse(validateEmailUsecase.isValidEmail(item))
        }
    }
}