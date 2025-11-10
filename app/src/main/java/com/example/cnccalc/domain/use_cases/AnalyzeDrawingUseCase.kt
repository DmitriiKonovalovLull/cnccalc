package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.data.model.DrawingAnalysis
import com.example.cnccalc.domain.repository.DrawingRepository
import javax.inject.Inject

class AnalyzeDrawingUseCase @Inject constructor(
    private val drawingRepository: DrawingRepository
) {
    suspend operator fun invoke(imagePath: String): DrawingAnalysis {
        return drawingRepository.analyzeDrawing(imagePath)
    }
}