package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materials")
data class MaterialEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String, // Храним MaterialType как String
    val hardness: Float,
    val tensileStrength: Float,
    val recommendedCuttingSpeed: Float,
    val recommendedFeedRate: Float,
    val description: String,
    val imageUrl: String? = null
) {
    fun toMaterial(): com.example.cnccalc.data.model.Material {
        return com.example.cnccalc.data.model.Material(
            id = id,
            name = name,
            type = type, // Оставляем как String, т.к. в data модели тоже String
            hardness = hardness,
            tensileStrength = tensileStrength,
            recommendedCuttingSpeed = recommendedCuttingSpeed,
            recommendedFeedRate = recommendedFeedRate,
            description = description,
            imageUrl = imageUrl
        )
    }
}

fun com.example.cnccalc.data.model.Material.toEntity(): MaterialEntity {
    return MaterialEntity(
        id = id,
        name = name,
        type = type,
        hardness = hardness,
        tensileStrength = tensileStrength,
        recommendedCuttingSpeed = recommendedCuttingSpeed,
        recommendedFeedRate = recommendedFeedRate,
        description = description,
        imageUrl = imageUrl
    )
}