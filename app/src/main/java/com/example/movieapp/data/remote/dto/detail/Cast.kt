package com.example.movieapp.data.remote.dto.detail

data class Cast(
    val adult: Boolean? = null,
    val cast_id: Int,
    val character: String,
    val credit_id: String? = null,
    val gender: Int,
    val id: Int,
    val known_for_department: String? = null,
    val name: String? = null,
    val order: Int? = null,
    val original_name: String? = null,
    val popularity: Double? = null,
    val profile_path: String? = null
)