package br.com.aisdigital.androidchallenge

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.aisdigital.androidchallenge.async.login.getUserData
import br.com.aisdigital.androidchallenge.data.models.User
import br.com.aisdigital.androidchallenge.intermediate.dataBus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            submit(it);
        }
    }

    fun submit(view: View) {

        userErrorText.visibility = View.INVISIBLE

        var email = txtEmail?.text
        var pass = txtPass?.text

        var resp: String?;

        if (email.isNullOrBlank() || pass.isNullOrBlank()) {
            userErrorText.visibility = View.VISIBLE
            //showError()
        } else {
            userErrorText.visibility = View.INVISIBLE
            userEmailError.visibility = View.INVISIBLE

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                userEmailError.visibility = View.VISIBLE
            } else {

                resp = dataBus().login(email.toString(), pass.toString());

                if (!resp?.startsWith("ERROR")!!) {

                    var respUser = getUserData().execute(resp).get()
                    var myType2 = object : TypeToken<User?>() {}.type
                    var jsonUser = Gson().fromJson<User?>(respUser, myType2)

                    showHome(jsonUser?.name, jsonUser?.age, jsonUser?.gender, email.toString())
                } else {
                    if (resp != null) {
                        resp = resp.substring(5, resp.length)
                        showError(resp)
                        userErrorText.visibility = View.VISIBLE
                    } else {
                        userErrorText.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    private fun showHome(name: String?, age: String?, gender: String?, email: String?) {
        val homeIntent = Intent(this, PrincipalActivity::class.java).apply {
            putExtra("name", name)
            putExtra("age", age)
            putExtra("gender", gender)
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    private fun showError(msj: String?) {
        val msjFail = AlertDialog.Builder(this)
        msjFail.setTitle("Error")
        msjFail.setMessage(msj)
        msjFail.setNeutralButton("OK", { dialogInterface: DialogInterface, i: Int -> })
        msjFail.show()
    }
}