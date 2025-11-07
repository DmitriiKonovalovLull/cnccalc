package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.model.DrawingAnalysis

interface DrawingRepository {
    suspend fun analyzeDrawing(imagePath: String): DrawingAnalysis
}