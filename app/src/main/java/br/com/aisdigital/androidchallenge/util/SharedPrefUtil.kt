package br.com.aisdigital.androidchallenge.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefUtil {

    companion object {
        const val TOKEN: String = "shared_token";

        private fun getShared(context: Context): SharedPreferences = run {
            return context.getSharedPreferences("shared", Context.MODE_PRIVATE);
        }

        fun putToken(context: Context, token: String) =
            run {
                val sharedPref = getShared(context);
                with(sharedPref.edit()) {
                    putString(TOKEN, token);
                    commit();
                }
            }

        fun getToken(context: Context): String? =
            run {
                val sharedPref = getShared(context);
                return sharedPref.getString(TOKEN, "");
            }

        fun removeToken(context: Context) =
            run {
                val sharedPref = getShared(context);
                with(sharedPref.edit()) {
                    remove(TOKEN);
                    commit();
                }
            }
    }
}