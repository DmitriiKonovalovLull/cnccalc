package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.data.models.DrawingAnalysis
import com.example.cnccalc.domain.repository.DrawingRepository
import javax.inject.Inject

class AnalyzeDrawingUseCase @Inject constructor(
    private val repository: DrawingRepository
) {
    suspend operator fun invoke(imagePath: String): DrawingAnalysis {
        return repository.analyzeDrawing(imagePath)
    }
}