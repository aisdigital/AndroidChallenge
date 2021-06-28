package br.com.aisdigital.androidchallenge

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.com.aisdigital.androidchallenge.retrofit.RequestService
import br.com.aisdigital.androidchallenge.util.SharedPrefUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), View.OnClickListener {

    @Inject
    lateinit var requestService: RequestService;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        findViewById<Button>(R.id.button_auth).setOnClickListener(this);
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_auth -> {
                onAuthClick();
            }
        }
    }

    private fun onAuthClick() {
        val username: String = findViewById<EditText>(R.id.edit_username).text.toString();
        val password: String = findViewById<EditText>(R.id.edit_password).text.toString();

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            makeToast("Login ou Senha incorreto.");
            return;
        }

        showProgressDialog("Logando...", "");

        val doAuth = Runnable {
            val response = requestService.postAuth(username, password).execute();
            if (response.isSuccessful) {
                response.body()?.token?.let {
                    SharedPrefUtil.putToken(this@MainActivity, it)
                };

                onLogin();
            } else {
                makeToast("Erro ao tentar realizar a autenticação. Tente novamente mais tarde");
            }
        }

        handlerThread = HandlerThread("Auth");
        handlerThread!!.start();
        handler = Handler(handlerThread!!.looper);
        handler!!.post(doAuth);
    }

    private fun onLogin() {
        val doLogin = Runnable {
            val response = requestService.getLogin().execute();
            if (response.isSuccessful) {
                makeToast(String.format("Logado com o usuário: %s", response.body()?.name));

                val intent = Intent(this@MainActivity, TeamListActivity::class.java);
                startActivity(intent)

            } else {
                makeToast("Erro ao tentar buscar os dados do usuário. Tente novamente mais tarde");
            }
        }

        handlerThread = HandlerThread("Login");
        handlerThread!!.start();
        handler = Handler(handlerThread!!.looper);
        handler!!.post(doLogin);
    }
}
