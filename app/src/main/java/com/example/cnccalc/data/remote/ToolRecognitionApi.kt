package com.example.cnccalc.data.remote

import android.graphics.Bitmap
import com.example.cnccalc.data.model.Tool

interface ToolRecognitionApi {
    suspend fun recognizeTool(image: Bitmap): Tool?
    suspend fun analyzeToolImage(imagePath: String): Tool?
}