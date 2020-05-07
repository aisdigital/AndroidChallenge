package br.com.aisdigital.androidchallenge.login

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.TextInputLayoutMatcher.Companion.hasTextInputLayoutHintText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)
    private lateinit var loginActivity: LoginActivity
    private lateinit var context: Context

    @Before fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        loginActivity = activityRule.activity
    }

    @Test fun signIn_should_show_error_message_on_input_when_a_input_is_empty () {
        onView(withId(R.id.btn_sign_in)).perform(click())
        onView(withId(R.id.tilEmail)).check(matches(hasTextInputLayoutHintText(context.getString(R.string.email_invalido))))
        onView(withId(R.id.tilPassword)).check(matches(hasTextInputLayoutHintText(context.getString(R.string.informe_uma_senha))))
    }

    @Test fun signIn_should_show_progressBar_when_all_inputs_is_not_empty () {
        onView(withId(R.id.et_login)).perform(replaceText("email@domain.com"))
        onView(withId(R.id.et_password)).perform(replaceText("123456"))
        onView(withId(R.id.et_password)).perform(pressImeActionButton())
        closeSoftKeyboard()
        onView(withId(R.id.btn_sign_in)).perform(click())

        onView(withId(R.id.btn_sign_in)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.progress)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}