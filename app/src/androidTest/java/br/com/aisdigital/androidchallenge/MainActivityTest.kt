package br.com.aisdigital.androidchallenge

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.TypeTextAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.aisdigital.androidchallenge.service.RepositoryImpl
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import com.nhaarman.mockitokotlin2.mock
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock


@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    @Test
    fun activityStartWithLoginFragmentTest(){
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(withId(R.id.username))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.password))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun enabledLoginButtonTest(){
        ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(withId(R.id.username)).perform(ViewActions.replaceText("teste"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.password)).perform(ViewActions.replaceText("teste"), closeSoftKeyboard())

        Espresso.onView(withId(R.id.login))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }
}
