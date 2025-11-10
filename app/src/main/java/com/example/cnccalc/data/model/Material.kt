package com.example.cnccalc.data.model

data class Material(
    val id: String,
    val name: String,
    val type: String,
    val hardness: Float,
    val tensileStrength: Float,
    val recommendedCuttingSpeed: Float,
    val recommendedFeedRate: Float,
    val description: String,
    val imageUrl: String? = null
) {
    fun getCuttingParameters(toolMaterial: String): CuttingParameters {
        // Расчет параметров резания на основе материала и инструмента
        val speedFactor = when (toolMaterial) {
            "hard alloy" -> 1.0f
            "hss" -> 0.6f
            else -> 0.8f
        }

        return CuttingParameters(
            cuttingSpeed = recommendedCuttingSpeed * speedFactor,
            feedRate = recommendedFeedRate
        )
    }
}

data class CuttingParameters(
    val cuttingSpeed: Float,
    val feedRate: Float
)
