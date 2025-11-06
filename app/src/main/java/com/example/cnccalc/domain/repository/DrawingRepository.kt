package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.models.DrawingAnalysis

interface DrawingRepository {
    suspend fun analyzeDrawing(imagePath: String): DrawingAnalysis
}