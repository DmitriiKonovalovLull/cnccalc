package com.example.cnccalc.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cnccalc.data.model.Tool
import com.example.cnccalc.domain.models.ToolType
import java.util.UUID

@Entity(tableName = "tools")
data class ToolEntity(
    @PrimaryKey
    @ColumnInfo(name = "tool_id")
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "type")
    val type: String, // Храним как String в БД

    @ColumnInfo(name = "diameter_mm")
    val diameter: Float,

    @ColumnInfo(name = "flute_count")
    val flutes: Int,

    @ColumnInfo(name = "material")
    val material: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "cutting_speed_m_min")
    val cuttingSpeed: Float, // м/мин

    @ColumnInfo(name = "feed_per_tooth_mm")
    val feedPerTooth: Float, // мм/зуб

    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,

    @ColumnInfo(name = "is_available")
    val isAvailable: Boolean = true,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
) {
    // Конвертация Entity в Data Model
    fun toTool(): Tool {
        return Tool(
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

// Функция расширения для конвертации Tool в ToolEntity
fun Tool.toEntity(): ToolEntity {
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
        imageUrl = imageUrl,
        isAvailable = true
    )
}
