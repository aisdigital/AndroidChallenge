package br.com.aisdigital.androidchallenge.ui.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.FragmentLoginBinding
import br.com.aisdigital.androidchallenge.ui.viewmodels.LoginViewModel
import br.com.aisdigital.androidchallenge.utilities.isEmailValid
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        binding.login.setOnClickListener {
            load(true)

            val email = binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                showErrorMessage(getString(R.string.email_password_empty))
            } else if (!email.isEmailValid()) {
                showErrorMessage(getString(R.string.please_enter_a_valid_email))
            } else if (password.length < 6) {
                showErrorMessage(getString(R.string.password_must_be_at_least_6_characters))
            } else {
                viewModel.login(email, password)
            }
        }

        viewModel.loginUser.observe(viewLifecycleOwner, { user ->
            if (user != null) {
                load(false)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        })

        return binding.root
    }

    private fun showErrorMessage(message: String) {
        load(false)

        val iMm =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        iMm.hideSoftInputFromWindow(requireView().windowToken, 0)
        requireView().clearFocus()

        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun load(isLoading: Boolean) {
        binding.email.isEnabled = !isLoading
        binding.password.isEnabled = !isLoading
        binding.login.isEnabled = !isLoading
        binding.load.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}