package br.com.aisdigital.androidchallenge.intermediate

import br.com.aisdigital.androidchallenge.async.login.getAccess
import br.com.aisdigital.androidchallenge.async.login.getUserData
import br.com.aisdigital.androidchallenge.data.models.Token
import br.com.aisdigital.androidchallenge.data.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class dataBus {

    fun login (user: String, pass: String) :String?{

        var resp: String? = getAccess().execute(user, pass).get()

        if (!resp?.startsWith("ERROR")!!) {
            var myType = object : TypeToken<Token?>() {}.type
            var jsonToken = Gson().fromJson<Token?>(resp, myType)
            var respToken = jsonToken?.token

            var respUser = getUserData().execute(respToken).get()

            if (!respUser?.startsWith("ERROR")!!) {
                var myType2 = object : TypeToken<User?>() {}.type
                var jsonUser = Gson().fromJson<User?>(respUser, myType2)
                resp = jsonUser.toString()
            }
            else {
                resp = respUser
            }
        }
        return resp;
    }
}