package br.com.aisdigital.androidchallenge

import android.app.Application
import br.com.aisdigital.androidchallenge.retrofit.RequestService
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ChallengeApplication : Application() {

    @Inject
    lateinit var requestService: RequestService;
}