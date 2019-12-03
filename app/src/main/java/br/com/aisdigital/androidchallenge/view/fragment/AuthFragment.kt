package br.com.aisdigital.androidchallenge.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.FragmentAuthBinding
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.viewmodel.AuthViewModel
import br.com.aisdigital.androidchallenge.viewmodel.ChallengeData
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment: Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private val viewModel: AuthViewModel by lazy {
        ViewModelProviders.of(this@AuthFragment).get(AuthViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dataLoading.observe(this@AuthFragment, Observer<Boolean> { loading ->
            pbAuth.visibility = if (loading) View.VISIBLE else View.INVISIBLE
        })

        viewModel.result.observe(this@AuthFragment, Observer<ChallengeData<Session>> { data ->
            if (data.error != null) {
                //TODO: Make string resource
                Toast.makeText(this@AuthFragment.context, "Erro: ${data.error?.localizedMessage}", Toast.LENGTH_LONG).show()
            } else {
                findNavController().navigate(R.id.action_authFragment_to_loginFragment)
            }

        })
    }
}