package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cnccalc.domain.models.MaterialType

@Entity(tableName = "materials")
data class MaterialEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val hardness: Float,
    val cuttingSpeed: Float,
    val feedRate: Float,
    val description: String
) {
    fun toMaterial(): com.example.cnccalc.data.model.Material {
        return com.example.cnccalc.data.model.Material(
            id = id,
            name = name,
            type = MaterialType.valueOf(type),
            hardness = hardness,
            cuttingSpeed = cuttingSpeed,
            feedRate = feedRate,
            description = description
        )
    }
}

fun com.example.cnccalc.data.model.Material.toEntity(): MaterialEntity {
    return MaterialEntity(
        id = id,
        name = name,
        type = type.name,
        hardness = hardness,
        cuttingSpeed = cuttingSpeed,
        feedRate = feedRate,
        description = description
    )
}