package br.com.aisdigital.androidchallenge.viewmodel

data class ChallengeData<T>(var data: T?, var error: Throwable?)

interface ChallengeViewModel {
}