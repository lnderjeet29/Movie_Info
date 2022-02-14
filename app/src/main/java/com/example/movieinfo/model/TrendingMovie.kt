package com.example.movieinfo.model

data class TrendingMovie(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)