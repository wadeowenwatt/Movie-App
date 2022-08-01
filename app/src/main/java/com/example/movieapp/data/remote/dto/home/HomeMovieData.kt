package com.example.movieapp.data.remote.dto.home

data class HomeMovieData(
    val dates: Dates?,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)