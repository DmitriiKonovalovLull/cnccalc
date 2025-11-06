package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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
            type = com.example.cnccalc.domain.models.ToolType.valueOf(type), // Конвертируем здесь
            diameter = diameter,
            flutes = flutes,
            material = material,
            description = description,
            cuttingSpeed = cuttingSpeed,
            feedPerTooth = feedPerTooth,
            imageUrl = imageUrl
        )
    }
    // Функция расширения для конвертации Tool в ToolEntity
    fun com.example.cnccalc.data.models.Tool.toEntity(): ToolEntity {
        return ToolEntity(
            id = id,
            name = name,
            type = type.name, // Конвертируем enum в string
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