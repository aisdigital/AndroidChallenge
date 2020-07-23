package br.com.aisdigital.androidchallenge.async.team

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class TeamApiTask() : AsyncTask<String, Void, String>() {

    val BASE_URL = "https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io"

    override fun doInBackground(vararg params: String?): String?{

        var result: String?


        val wsTeam: String = "$BASE_URL/teams"
        var url: URL? = null
        try {
            url = URL(wsTeam)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
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
                result = "$responseCode $responseMessage"
            }
        }
        catch (e: MalformedURLException){
            Log.e("ERROR",e.message)
            throw java.lang.Exception("Error: ${e.message}")
        }
        catch (e: IOException){
            Log.e("ERROR",e.message)
            throw java.lang.Exception("Error: ${e.message}")
        }
        catch (e: JSONException){
            Log.e("ERROR",e.message)
            throw java.lang.Exception("Error: ${e.message}")
        }
        catch (e: Exception){
            Log.e("ERROR",e.message)
            throw java.lang.Exception("Error: ${e.message}")
        }
        return result
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

}