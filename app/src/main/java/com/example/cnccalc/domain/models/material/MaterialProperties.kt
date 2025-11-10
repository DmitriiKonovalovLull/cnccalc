package com.example.cnccalc.domain.models.material

data class MaterialProperties(
    val hardnessHB: Float,
    val tensileStrength: Float,
    val yieldStrength: Float,
    val elongation: Float,
    val thermalConductivity: Float,
    val machinability: Float
)