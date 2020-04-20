package br.com.aisdigital.androidchallenge.ui

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class EmailValidatorTest {

    @Test
    fun `when invalid email is informed should return false`() {
        assertAll(
            { Assertions.assertFalse("arnold".isEmail()) },
            { Assertions.assertFalse("arnold@".isEmail()) },
            { Assertions.assertFalse("arnold@gmail".isEmail()) },
            { Assertions.assertFalse("arnold.com".isEmail()) },
            { Assertions.assertFalse("arnold@gmail.com.".isEmail()) }
        )
    }

    @Test
    fun `when valid email is informed should return true`() {
        assertAll(
            { Assertions.assertTrue("arnold@gmail.com".isEmail()) },
            { Assertions.assertTrue("arnold@gmail.com.br".isEmail()) }
        )
    }
}