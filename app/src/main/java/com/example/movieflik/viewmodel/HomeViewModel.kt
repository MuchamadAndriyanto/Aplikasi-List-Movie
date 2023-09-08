package com.example.movieflik.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieflik.model.PopularMovie
import com.example.movieflik.model.Result
import com.example.movieflik.model.ResultX
import com.example.movieflik.model.TopRatedMovie
import com.example.movieflik.network.ApiClient
import com.example.movieflik.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private var api : ApiService): ViewModel() {

    var livedatamoviePopular : MutableLiveData<List<Result>> = MutableLiveData()
    var livedatamovieTopRated : MutableLiveData<List<ResultX>> = MutableLiveData()


    fun getlivedatamoviePopular(): MutableLiveData<List<Result>> {
        return livedatamoviePopular
    }

    fun getlivedatamovieTopRated(): MutableLiveData<List<ResultX>> {
        return livedatamovieTopRated
    }

    fun getmoviePopular() {
        api.getmoviePopular().enqueue(object : Callback<PopularMovie> {
           override fun onResponse(call: Call<PopularMovie>, response: Response<PopularMovie>
           ) {

               livedatamoviePopular.value = response.body()?.results

           }

           override fun onFailure(call: Call<PopularMovie>, t: Throwable) {
               Log.d("Tag", t.message.toString())

           }

       })
    }

    fun getmovieToprated() {
        api.getmovieTopRated().enqueue(object : Callback<TopRatedMovie> {
            override fun onResponse(call: Call<TopRatedMovie>, response: Response<TopRatedMovie>
            ) {

                livedatamovieTopRated.value = response.body()?.results

            }

            override fun onFailure(call: Call<TopRatedMovie>, t: Throwable) {
                Log.d("Tag", t.message.toString())

            }

        })
    }


}