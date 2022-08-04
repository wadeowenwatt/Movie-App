package com.example.movieapp.presentation.search

import com.example.movieapp.domain.model.Search
import com.example.movieapp.domain.model.SearchTypeModel

data class SearchUiState(
    val isLoading: Boolean = false,
    val search: List<Search> = emptyList(),
    val error: String = ""
)