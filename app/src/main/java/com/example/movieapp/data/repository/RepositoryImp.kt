package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.remote.dto.detail.DetailMovieData
import com.example.movieapp.data.remote.dto.home.HomeMovieData
import com.example.movieapp.data.remote.dto.home.Result
import com.example.movieapp.domain.irepo.IRepository
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val api: MovieApi) : IRepository {
    override suspend fun getPopular(): HomeMovieData {
        return api.getPopular()
    }

    override suspend fun getUpcoming(): HomeMovieData {
        return api.getUpcoming()
    }

    override suspend fun getDetail(id: Int): DetailMovieData {
        return api.getMovieDetail(id)
    }

    override suspend fun getSearch(query: String): HomeMovieData {
        return api.getSearch(query)
    }
}