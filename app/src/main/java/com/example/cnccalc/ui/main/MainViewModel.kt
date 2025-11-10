package com.example.cnccalc.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnccalc.domain.use_cases.FindToolsUseCase
import com.example.cnccalc.domain.use_cases.SearchToolsOnlineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val findToolsUseCase: FindToolsUseCase,
    private val searchToolsOnlineUseCase: SearchToolsOnlineUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadTools()
    }

    private fun loadTools() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            try {
                findToolsUseCase().collect { tools ->
                    _uiState.value = MainUiState.Success(tools)
                }
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun searchTools(query: String) {
        viewModelScope.launch {
            // TODO: Реализовать поиск
        }
    }
}

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val tools: List<com.example.cnccalc.data.model.Tool>) : MainUiState()
    data class Error(val message: String) : MainUiState()
}