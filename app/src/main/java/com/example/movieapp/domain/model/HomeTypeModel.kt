package com.example.movieapp.domain.model

import com.example.movieapp.data.remote.dto.home.Result

data class HomeTypeModel(
    val listPopular : List<Home>,
    val listUpcoming : List<Home>
)