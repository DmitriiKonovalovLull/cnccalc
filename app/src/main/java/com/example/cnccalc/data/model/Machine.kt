package com.example.cnccalc.data.model

data class Machine(
    val id: String,
    val name: String,
    val type: String,
    val maxRPM: Int,
    val power: Float,
    val workArea: String,
    val description: String,
    val model: String,
    val manufacturer: String
) {
    fun canHandleTool(toolDiameter: Float): Boolean {
        // Простая проверка - если станок фрезерный, проверяем диаметр
        return when (type.lowercase()) {
            "mill", "фрезерный" -> toolDiameter <= 50f // примерное ограничение
            "lathe", "токарный" -> toolDiameter <= 25f
            else -> true
        }
    }

    fun getMaxFeedRate(): Float {
        return when (type.lowercase()) {
            "mill", "фрезерный" -> 10000f // мм/мин
            "lathe", "токарный" -> 5000f
            else -> 3000f
        }
    }
}