package com.example.cnccalc.data.model

data class DrawingAnalysis(
    val contours: List<Contour>,
    val dimensions: Map<String, Float>,
    val features: List<String>,
    val recommendedTools: List<Tool>
)

data class Contour(
    val points: List<Point>,
    val type: String
)

data class Point(
    val x: Float,
    val y: Float
)