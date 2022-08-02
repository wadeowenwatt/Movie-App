package com.example.movieapp.presentation.detail

import com.example.movieapp.domain.model.Detail

data class DetailUiState (
    val isLoading: Boolean = false,
    val detail: List<Detail> = emptyList(),
    val error: String = ""
)