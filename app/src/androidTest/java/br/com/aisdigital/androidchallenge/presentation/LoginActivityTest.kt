package br.com.aisdigital.androidchallenge.presentation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.aisdigital.androidchallenge.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Test
    fun checkStartOfActivityLogin() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.emailField)).check(matches(isDisplayed()))

        onView(withId(R.id.pwdField)).check(matches(isDisplayed()))

        onView(withId(R.id.loginButton)).check(matches(isDisplayed()))
    }

    @Test
    fun checkClickOnButtonLoginWithEmptyFields() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.emailField))
            .check(matches(hasErrorText("Este campo é obrigatório")))

        onView(withId(R.id.emailField))
            .perform(typeText("teste@email.com"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.pwdField))
            .check(matches(hasErrorText("Este campo é obrigatório")))
    }
}