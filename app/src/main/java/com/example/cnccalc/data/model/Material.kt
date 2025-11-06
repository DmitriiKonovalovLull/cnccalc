package com.example.cnccalc.data.model

import com.example.cnccalc.domain.models.MaterialType

data class Material(
    val id: String,
    val name: String,
    val type: MaterialType,
    val hardness: Float,
    val cuttingSpeed: Float,
    val feedRate: Float,
    val description: String
)