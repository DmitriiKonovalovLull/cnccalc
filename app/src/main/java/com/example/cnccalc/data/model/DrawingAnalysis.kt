package com.example.cnccalc.data.model

data class DrawingAnalysis(
    val id: String,
    val imagePath: String,
    val contours: List<Contour>,
    val dimensions: Dimensions,
    val recommendedTools: List<String>, // IDs инструментов
    val estimatedMachiningTime: Float, // минут
    val complexity: ComplexityLevel,
    val analyzedAt: Long
) {
    fun getRequiredOperations(): List<String> {
        val operations = mutableListOf<String>()
        contours.forEach { contour ->
            when (contour.type) {
                ContourType.HOLE -> operations.add("drilling")
                ContourType.POCKET -> operations.add("pocket_milling")
                ContourType.PROFILE -> operations.add("contour_milling")
                ContourType.SLOT -> operations.add("slot_milling")
            }
        }
        return operations.distinct()
    }

    fun isValidForMachine(machineWorkArea: String): Boolean {
        return dimensions.width <= parseWorkAreaX(machineWorkArea) &&
                dimensions.height <= parseWorkAreaY(machineWorkArea)
    }

    private fun parseWorkAreaX(workArea: String): Float {
        // Парсим строку рабочей области "300x400x200"
        return workArea.split("x")[0].toFloatOrNull() ?: 0f
    }

    private fun parseWorkAreaY(workArea: String): Float {
        return workArea.split("x")[1].toFloatOrNull() ?: 0f
    }
}

data class Contour(
    val type: ContourType,
    val points: List<Point>,
    val area: Float,
    val perimeter: Float
)

data class Dimensions(
    val width: Float,
    val height: Float,
    val depth: Float
)

data class Point(
    val x: Float,
    val y: Float
)

enum class ContourType {
    HOLE, POCKET, PROFILE, SLOT
}

enum class ComplexityLevel {
    SIMPLE, MEDIUM, COMPLEX
}