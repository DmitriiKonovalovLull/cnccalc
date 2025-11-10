package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.model.DrawingAnalysis
import com.example.cnccalc.data.model.Tool
import kotlinx.coroutines.flow.Flow

interface DrawingRepository {
    suspend fun analyzeDrawing(imagePath: String): DrawingAnalysis
    suspend fun getRecommendedTools(analysis: DrawingAnalysis): List<Tool>
    suspend fun generateGCode(analysis: DrawingAnalysis, tool: Tool): String
    fun getAnalysisHistory(): Flow<List<DrawingAnalysis>>
    suspend fun saveAnalysis(analysis: DrawingAnalysis)
    suspend fun deleteAnalysis(analysis: DrawingAnalysis)
}