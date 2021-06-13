package br.com.aisdigital.androidchallenge.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import br.com.aisdigital.androidchallenge.data.network.AuthApi
import br.com.aisdigital.androidchallenge.data.network.Resource
import br.com.aisdigital.androidchallenge.data.repository.AuthRepository
import br.com.aisdigital.androidchallenge.databinding.FragmentLoginBinding
import br.com.aisdigital.androidchallenge.ui.base.BaseFragment
import br.com.aisdigital.androidchallenge.ui.enable
import br.com.aisdigital.androidchallenge.ui.home_user.HomeUserActivity
import br.com.aisdigital.androidchallenge.ui.startNewActivity
import br.com.aisdigital.androidchallenge.ui.visible


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.authResponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    viewModel.saveAuthToken(it.value.token)
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                    requireActivity().startNewActivity(HomeUserActivity::class.java)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Auth Failure", Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.passwordText.addTextChangedListener {
            val email = binding.emailText.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.emailText.text.toString().trim()
            val password = binding.passwordText.text.toString().trim()
            viewModel.auth(email, password)

            binding.progressbar.visible(false)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}