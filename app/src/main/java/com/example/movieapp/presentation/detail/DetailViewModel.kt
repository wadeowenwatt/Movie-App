package com.example.movieapp.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.config.Resource
import com.example.movieapp.domain.use_case.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getDetailUseCase: GetDetailUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    var movieId = 0

    fun run() {
        getDetailUseCase.id = movieId
        getDetail()
    }

    private fun getDetail() {
        getDetailUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = DetailUiState(detail = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _uiState.value =
                        DetailUiState(error = result.message ?: "An unexpected error occurred.")
                }
                is Resource.Loading -> {
                    _uiState.value = DetailUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}