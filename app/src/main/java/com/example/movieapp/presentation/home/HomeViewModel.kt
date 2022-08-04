package com.example.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.config.Resource
import com.example.movieapp.domain.use_case.GetHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getHomeUseCase: GetHomeUseCase) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getHome()
    }

    fun refresh() {

        getHome()
    }

    private fun getHome() {
        getHomeUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = HomeUiState(home = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _uiState.value =
                        HomeUiState(error = result.message ?: "An unexpected error occurred.")
                }
                is Resource.Loading -> {
                    _uiState.value = HomeUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}