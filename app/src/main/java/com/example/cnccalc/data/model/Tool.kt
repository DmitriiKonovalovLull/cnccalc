package com.example.cnccalc.data.model

import com.example.cnccalc.domain.models.ToolType

data class Tool(
    val id: String,
    val name: String,
    val type: ToolType,
    val diameter: Float,
    val flutes: Int,
    val material: String,
    val description: String,
    val cuttingSpeed: Float,
    val feedPerTooth: Float,
    val imageUrl: String? = null
) {
    fun suitableFor(material: String, operation: String): Boolean {
        val isMaterialCompatible = when (this.material.lowercase()) {
            "hard alloy", "твердый сплав" -> material.contains("steel", ignoreCase = true) ||
                    material.contains("aluminum", ignoreCase = true)
            "hss", "быстрорежущая сталь" -> !material.contains("hard", ignoreCase = true)
            else -> true
        }

        val isOperationCompatible = when (type) {
            ToolType.DRILL -> operation.contains("drill", ignoreCase = true)
            ToolType.END_MILL -> operation.contains("mill", ignoreCase = true) ||
                    operation.contains("profile", ignoreCase = true)
            ToolType.TAP -> operation.contains("thread", ignoreCase = true)
            else -> true
        }

        return isMaterialCompatible && isOperationCompatible
    }

    fun calculateRPM(materialCuttingSpeed: Float): Int {
        return (materialCuttingSpeed * 1000 / (Math.PI * diameter)).toInt()
    }

    fun calculateFeedRate(rpm: Int): Float {
        return rpm * feedPerTooth * flutes
    }
}