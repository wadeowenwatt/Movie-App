package com.example.movieapp.data.remote.dto

import com.example.movieapp.data.remote.dto.category.Genre
import com.example.movieapp.data.remote.dto.detail.DetailMovieData
import com.example.movieapp.data.remote.dto.home.Result
import com.example.movieapp.domain.model.*

fun Result.toHome(): Home{
    return Home(
        id = id,
        imdb = vote_average.toString(),
        title = title,
        image = backdrop_path,
        poster = poster_path
    )
}

fun DetailMovieData.toDetail() : Detail {
    return Detail(
        adult = adult,
        title = title,
        banner = poster_path,
        description = overview,
        imdb = vote_average.toString(),
        genres = genres,
        cast = credits?.cast
    )
}

fun Genre.toCategory() : Categories {
    return Categories(
        id = id,
        name = name
    )
}

fun Result.toSearch() : Search {
    return Search(
        id = id,
        name = title,
        image = backdrop_path!!
    )
}

// CategoryDetail not now

