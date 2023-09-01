package com.example.movieflik.network

import com.example.movieflik.model.PopularMovie
import com.example.movieflik.model.TopRatedMovie
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=5eadf04193e2a9bcea8a8e8f4cfa89e2&language=en-US&page=1")
    fun getmoviePopular(): Call<PopularMovie>

    @GET("movie/top_rated?api_key=5eadf04193e2a9bcea8a8e8f4cfa89e2&language=en-US&page=1")
    fun getmovieTopRated(): Call<TopRatedMovie>

}