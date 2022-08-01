package com.example.movieapp.presentation.home

import com.example.movieapp.domain.model.HomeTypeModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val home: List<HomeTypeModel> = emptyList(),
    val error: String = ""
)