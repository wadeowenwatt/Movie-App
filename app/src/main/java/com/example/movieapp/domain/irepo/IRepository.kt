package com.example.movieapp.domain.irepo

import com.example.movieapp.data.remote.dto.detail.DetailMovieData
import com.example.movieapp.data.remote.dto.home.HomeMovieData
import com.example.movieapp.data.remote.dto.home.Result

interface IRepository {
    suspend fun getPopular() : HomeMovieData
    suspend fun getUpcoming() : HomeMovieData
    suspend fun getDetail(id: Int) : DetailMovieData
    suspend fun getSearch(query: String): HomeMovieData
    // Category not now

}