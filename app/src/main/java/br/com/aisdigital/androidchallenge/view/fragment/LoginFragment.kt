package br.com.aisdigital.androidchallenge.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.FragmentLoginBinding
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.domain.User
import br.com.aisdigital.androidchallenge.viewmodel.AuthViewModel
import br.com.aisdigital.androidchallenge.viewmodel.ChallengeData
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this@LoginFragment).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel

        binding.lifecycleOwner = this

        viewModel.login()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dataLoading.observe(this@LoginFragment, Observer<Boolean> { loading ->
            pbLogin.visibility = if (loading) View.VISIBLE else View.INVISIBLE
        })

        viewModel.result.observe(this@LoginFragment, Observer<ChallengeData<User>> { data ->
            if (data.error != null) {
                //TODO: Make string resource
                Toast.makeText(this@LoginFragment.context, "Erro: ${data.error?.localizedMessage}", Toast.LENGTH_LONG).show()
            }

        })
    }

}
