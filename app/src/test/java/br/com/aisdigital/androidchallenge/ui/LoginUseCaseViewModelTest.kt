package br.com.aisdigital.androidchallenge.ui


import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.core.domain.usecase.LoginUseCase
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class LoginUseCaseViewModelTest {

    private val loginUseCase: LoginUseCase = mockk(relaxed = true)
    private val observer: Observer<LoginViewState> = mockk(relaxed = true)
    lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun setUp() {
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun `When email is blank should return an error`() {
        viewModel.viewState.observeForever(observer)
        viewModel.onLoginClick("", "password")

        assert(viewModel.viewState.value == LoginViewState.ShowEmailRequiredError)
    }

    @Test
    fun `When email is invalid should return an error`() {
        viewModel.viewState.observeForever(observer)
        viewModel.onLoginClick("ais@", "password")

        assert(viewModel.viewState.value == LoginViewState.ShowInvalidEmailError)
    }

    @Test
    fun `When password is blank should return an error`() {
        viewModel.viewState.observeForever(observer)
        viewModel.onLoginClick("arnold@ais.com.br", "")

        assert(viewModel.viewState.value == LoginViewState.ShowPasswordRequiredError)
    }
}