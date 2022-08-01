package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.dto.category.CategoryMovieData
import com.example.movieapp.data.remote.dto.detail.DetailMovieData
import com.example.movieapp.data.remote.dto.home.HomeMovieData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/popular?api_key=b6089aedb1274752de2f1022865a15ac")
    suspend fun getPopular(): HomeMovieData

    @GET("/3/movie/upcoming?api_key=b6089aedb1274752de2f1022865a15ac")
    suspend fun getUpcoming(): HomeMovieData

    @GET("/3/movie/{id}?api_key=b6089aedb1274752de2f1022865a15ac")
    suspend fun getMovieDetail(@Path("id") id: Int): DetailMovieData

    @GET("/3/genre/movie/list?api_key=b6089aedb1274752de2f1022865a15ac")
    suspend fun getCategories(): CategoryMovieData

    @GET("/3/discover/movie?api_key=b6089aedb1274752de2f1022865a15ac")
    suspend fun getCategoryDetail(@Query("with_genres") id: Int): HomeMovieData

    @GET("/3/search/movie?api_key=b6089aedb1274752de2f1022865a15ac")
    suspend fun getSearch(@Query("query") query: String): HomeMovieData
}