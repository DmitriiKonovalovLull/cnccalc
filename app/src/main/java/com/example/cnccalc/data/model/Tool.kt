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
        return this.material.contains(material, ignoreCase = true)
    }
}