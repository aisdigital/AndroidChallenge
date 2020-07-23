package br.com.aisdigital.androidchallenge.async.login

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder


class getUserData() : AsyncTask<String, Void, String>() {

    val BASE_URL = "https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io"

    override fun doInBackground(vararg params: String?): String?{

        var result: String?

        val wsLogin: String = "$BASE_URL/login"
        var url: URL? = null

        try {

            url = URL("$wsLogin?x-auth-token=$params[0]")
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.readTimeout = 15000
            urlConnection.connectTimeout = 15000
            urlConnection.requestMethod = "GET"
            urlConnection.instanceFollowRedirects = false

            val responseCode = urlConnection.responseCode
            val responseMessage = urlConnection.responseMessage

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val istram = BufferedReader(InputStreamReader(urlConnection.inputStream))
                result = istram.readText()
                istram.close()
            }
            else {
                Log.i("INFO","$responseCode $responseMessage")
                result = "ERROR$responseCode $responseMessage"
            }
        }
        catch (e: MalformedURLException){
            Log.e("ERROR",e.message)
            //throw java.lang.Exception("Error: ${e.message}")
            result = "ERROR"+ e.message
            return result
        }
        catch (e: IOException){
            Log.e("ERROR",e.message)
            //throw java.lang.Exception("Error: ${e.message}")
            result = "ERROR"+ e.message
            return result
        }
        catch (e: JSONException){
            Log.e("ERROR",e.message)
            //throw java.lang.Exception("Error: ${e.message}")
            result = "ERROR"+ e.message
            return result
        }
        catch (e: Exception){
            Log.e("ERROR",e.message)
            //throw java.lang.Exception("Error: ${e.message}")
            result = "ERROR"+ e.message
            return result
        }
        return result
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    //Transformar JSON Obejct a String *******************************************
    @Throws(java.lang.Exception::class)
    fun getPostDataString(params: JSONObject): String? {
        val result = StringBuilder()
        var first = true
        val itr = params.keys()
        while (itr.hasNext()) {
            val key = itr.next()
            val value = params[key]
            if (first) first = false else result.append("&")
            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value.toString(), "UTF-8"))
        }
        return result.toString()
    }

}