package br.com.aisdigital.androidchallenge

import android.R
import android.app.ProgressDialog
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper

open class BaseActivity : AppCompatActivity() {

    var handler: Handler? = null;
    var handlerThread: HandlerThread? = null;
    var progressDialog: ProgressDialog? = null;

    fun makeToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show();
    }

    open fun showProgressDialog(title: String?, message: String?) {
        if (!isFinishing) {
            progressDialog =
                ProgressDialog(ContextThemeWrapper(this, R.style.Theme_Holo_Light_Dialog));

            if (!title.isNullOrEmpty()) progressDialog!!.setTitle(title);
            if (!message.isNullOrEmpty()) progressDialog!!.setMessage(message);

            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog!!.setCancelable(false);
            progressDialog!!.setIndeterminate(true);
            progressDialog!!.show();
        }
    }

    open fun closeProgressDialog() {
        if (!isFinishing && progressDialog != null) {
            try {
                progressDialog!!.dismiss();
            } catch (e: Exception) {
                e.printStackTrace();
            }
        }
    }
}