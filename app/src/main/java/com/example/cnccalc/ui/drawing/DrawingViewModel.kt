package com.example.cnccalc.ui.drawing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnccalc.domain.use_cases.AnalyzeDrawingUseCase
import com.example.cnccalc.domain.use_cases.FindToolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawingViewModel @Inject constructor(
    private val analyzeDrawingUseCase: AnalyzeDrawingUseCase,
    private val findToolsUseCase: FindToolsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DrawingUiState>(DrawingUiState.Idle)
    val uiState: StateFlow<DrawingUiState> = _uiState

    fun analyzeDrawing(imagePath: String) {
        viewModelScope.launch {
            _uiState.value = DrawingUiState.Loading
            try {
                val analysis = analyzeDrawingUseCase(imagePath)
                _uiState.value = DrawingUiState.Success(analysis)
            } catch (e: Exception) {
                _uiState.value = DrawingUiState.Error(e.message ?: "Analysis failed")
            }
        }
    }
}

sealed class DrawingUiState {
    object Idle : DrawingUiState()
    object Loading : DrawingUiState()
    data class Success(val analysis: com.example.cnccalc.data.models.DrawingAnalysis) : DrawingUiState()
    data class Error(val message: String) : DrawingUiState()
}