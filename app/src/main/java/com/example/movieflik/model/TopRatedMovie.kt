package com.example.movieflik.model

data class TopRatedMovie(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)