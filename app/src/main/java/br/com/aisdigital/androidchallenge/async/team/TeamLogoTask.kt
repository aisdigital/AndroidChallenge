package br.com.aisdigital.androidchallenge.async.team

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

class TeamLogoTask() : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg params: String?): Bitmap? {
        return BitmapFactory.decodeStream(getUrl(URL(params[0]))?.openConnection()?.getInputStream())
    }

    private fun getUrl(url: URL): URL?{
        try {
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            con.instanceFollowRedirects = false
            con.connect()
            val resCode: Int = con.responseCode
            if (resCode == HttpURLConnection.HTTP_SEE_OTHER || resCode == HttpURLConnection.HTTP_MOVED_PERM || resCode == HttpURLConnection.HTTP_MOVED_TEMP
            ) {
                var location: String = con.getHeaderField("Location")
                if (location.startsWith("/")) {
                    location = url.protocol.toString() + "://" + url.host + location
                }
                return getUrl(URL(location))
            }else{
                return url
            }
        } catch (e: Exception) {
            Log.e("ERROR",e.message)
        }
        return url
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
    }
}