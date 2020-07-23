package br.com.aisdigital.androidchallenge.utils

import android.graphics.Bitmap
import android.util.Log
import br.com.aisdigital.androidchallenge.async.team.TeamLogoTask

class Utilities {
    fun getTeamImage(url:String): Bitmap?{
        try {
            val teamLogoTask: TeamLogoTask = TeamLogoTask()
            return teamLogoTask.execute(url).get()
        }
        catch (ex: Exception){
            Log.e("ERROR",ex.message)
            return null
        }
    }
}