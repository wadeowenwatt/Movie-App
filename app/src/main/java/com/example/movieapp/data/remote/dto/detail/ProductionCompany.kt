package com.example.movieapp.data.remote.dto.detail

data class ProductionCompany(
    val id: Int,
    val logo_path: Any? = null,
    val name: String,
    val origin_country: String
)