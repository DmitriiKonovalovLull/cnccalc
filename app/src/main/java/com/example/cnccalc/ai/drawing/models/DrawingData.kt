package com.example.cnccalc.ai.drawing.models

data class DrawingData(
    val contours: List<Contour>,
    val dimensions: Map<String, Float>,
    val features: List<Feature>
)

data class Contour(
    val points: List<Point>,
    val type: ContourType
)

data class Point(
    val x: Float,
    val y: Float
)

enum class ContourType {
    LINE, CIRCLE, ARC, RECTANGLE
}

data class Feature(
    val type: String,
    val parameters: Map<String, Float>
)