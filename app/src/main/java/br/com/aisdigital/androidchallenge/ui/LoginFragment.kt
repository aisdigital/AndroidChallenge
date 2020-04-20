package br.com.aisdigital.androidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.aisdigital.androidchallenge.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
        observeViewState()
    }

    private fun setClickListeners() {
        login.setOnClickListener {
            viewModel.onLoginClick(email.text.toString(), password.text.toString())
        }
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            renderView(it)
        })
    }

    private fun renderView(viewState: LoginViewState) {
        when (viewState) {
            is LoginViewState.OnSuccess -> findNavController().popBackStack(
                R.id.userProfileFragment,
                false
            )
            is LoginViewState.ShowEmailRequiredError -> {
                email.error = getString(R.string.required)
                email.requestFocus()
            }
            is LoginViewState.ShowInvalidEmailError -> {
                email.error = getString(R.string.invalid_email)
                email.requestFocus()
            }
            is LoginViewState.ShowPasswordRequiredError -> {
                password.error = getString(R.string.required)
                password.requestFocus()
            }
            else -> Toast.makeText(
                requireContext(),
                getString(R.string.please_try_again),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}