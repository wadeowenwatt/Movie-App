package com.example.movieapp.domain.model

import com.example.movieapp.data.remote.dto.detail.Cast
import com.example.movieapp.data.remote.dto.detail.Genre

data class Detail(
    val adult: Boolean,
    val title: String?,
    val banner: String?,
    val description: String?,
    val imdb: String?,
    val genres: List<Genre>,
    val cast: List<Cast>?
)