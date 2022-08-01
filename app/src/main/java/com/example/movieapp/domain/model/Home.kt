package com.example.movieapp.domain.model

import com.example.movieapp.data.remote.dto.home.Result

data class Home(
    val id: Int,
    val title: String?,
    val imdb:String?,
    val image: String?,
    val poster:String?
)