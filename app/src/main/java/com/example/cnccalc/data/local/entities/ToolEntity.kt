package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cnccalc.domain.models.ToolType

@Entity(tableName = "tools")
data class ToolEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val diameter: Float,
    val flutes: Int,
    val material: String,
    val description: String,
    val cuttingSpeed: Float,
    val feedPerTooth: Float,
    val imageUrl: String? = null
) {
    fun toTool(): com.example.cnccalc.data.models.Tool {
        return com.example.cnccalc.data.models.Tool(
            id = id,
            name = name,
            type = ToolType.valueOf(type),
            diameter = diameter,
            flutes = flutes,
            material = material,
            description = description,
            cuttingSpeed = cuttingSpeed,
            feedPerTooth = feedPerTooth,
            imageUrl = imageUrl
        )
    }
}

fun com.example.cnccalc.data.models.Tool.toEntity(): ToolEntity {
    return ToolEntity(
        id = id,
        name = name,
        type = type.name,
        diameter = diameter,
        flutes = flutes,
        material = material,
        description = description,
        cuttingSpeed = cuttingSpeed,
        feedPerTooth = feedPerTooth,
        imageUrl = imageUrl
    )
}