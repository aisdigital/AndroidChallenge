package br.com.aisdigital.androidchallenge.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import br.com.aisdigital.androidchallenge.BR
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.databinding.ActivityLoginBinding
import br.com.aisdigital.androidchallenge.viewmodel.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val mViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this@LoginActivity
        binding.setVariable(BR.viewModel, mViewModel)

        setEditorAction()
    }

    private fun setEditorAction() {
        binding.loginPasswordInputEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                mViewModel.onLoginClick()
                true
            } else {
                false
            }
        }
    }

}
