package br.com.aisdigital.androidchallenge.data.team

import br.com.aisdigital.androidchallenge.model.Team
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TeamRepository(private val api: TeamApi) {

    fun getAll(success: (List<Team>) -> Unit, failure: () -> Unit) {
        api.getAll()
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<List<Team>>() {
                override fun onSuccess(value: List<Team>?) {
                    success(value!!)
                }

                override fun onError(e: Throwable?) {
                    failure()
                }
            })
    }
}