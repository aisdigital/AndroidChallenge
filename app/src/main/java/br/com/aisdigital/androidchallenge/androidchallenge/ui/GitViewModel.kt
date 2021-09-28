package br.com.aisdigital.androidchallenge.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.aisdigital.androidchallenge.data.Address
import br.com.aisdigital.androidchallenge.data.InterfaceApi
import br.com.aisdigital.androidchallenge.data.Retrofitinstance
import br.com.aisdigital.androidchallenge.model.Team
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitViewModel(application: Application) : AndroidViewModel(application) {

    var mutableLiveData: MutableLiveData<ArrayList<Team>>

    init {
        mutableLiveData = MutableLiveData()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<ArrayList<Team>> {
        return mutableLiveData
    }

    fun getRepos(page: Int = 1) :  ArrayList<Team> {
        val address = Address()
        val retrofitInstance = Retrofitinstance.getRetrofitInstance().create(InterfaceApi::class.java)
        var data = ArrayList<Team>()

        val call = retrofitInstance.ApiGetReposResult()


        call.enqueue(object : Callback<List<Team>>  {
            override fun onFailure(call: Call<List<Team>>, t: Throwable) {
                Log.d("Erro", "Erro" + call.request())
            }

            override fun onResponse(call: Call<List<Team>>, response: Response<List<Team>>) {
                var itemItem = response.body()

                response.body()?.forEach(){
                    data.add(it)
                    Log.d("Sucesso", "sucesso" + it.toString())
                }
                mutableLiveData.postValue(data)

            }
        })
        return data
    }
}


