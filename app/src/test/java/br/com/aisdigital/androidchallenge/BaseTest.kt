package br.com.aisdigital.androidchallenge

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import br.com.aisdigital.androidchallenge.di.DaggerAppComponent
import br.com.aisdigital.androidchallenge.module.AppModuleTest
import br.com.aisdigital.androidchallenge.module.BackendModuleTest
import br.com.aisdigital.androidchallenge.module.RemoteModuleTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = AppTest::class)
abstract class BaseTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var app: AppTest

    lateinit var context: Context

    @ExperimentalCoroutinesApi
    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)

        Dispatchers.setMain(mainThreadSurrogate)

        Mockito.`when`(app.component).thenReturn(
            DaggerAppComponent.builder()
                .appModule(AppModuleTest(app))
                .backendModule(BackendModuleTest())
                .remoteModule(RemoteModuleTest())
                .build())

        context = ApplicationProvider.getApplicationContext()

    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}