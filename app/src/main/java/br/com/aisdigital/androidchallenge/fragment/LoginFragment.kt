package br.com.aisdigital.androidchallenge.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.aisdigital.androidchallenge.R
import br.com.aisdigital.androidchallenge.events.UserLoginEvent
import br.com.aisdigital.androidchallenge.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.greenrobot.eventbus.EventBus
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.user.observe(this, Observer {
            loading.visibility = View.GONE
            EventBus.getDefault().post(UserLoginEvent(it))
        })

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            viewModel.login(username.text.toString(), password.text.toString())
        }

        username.addTextChangedListener(textChange)
        password.addTextChangedListener(textChange)
    }
    private val textChange = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            validateLoginButton()
        }
    }

    //TODO:Improve this validation for accept only valid e-mail
    private fun validateLoginButton() {
        login.isEnabled = username.length() > 0 && password.length() > 0;
    }

}