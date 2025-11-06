package com.example.cnccalc.data.local.database

import androidx.room.TypeConverter
import com.example.cnccalc.domain.models.ToolType
import com.example.cnccalc.domain.models.MaterialType

class Converters {

    @TypeConverter
    fun fromToolType(toolType: ToolType): String {
        return toolType.name
    }

    @TypeConverter
    fun toToolType(value: String): ToolType {
        return ToolType.valueOf(value)
    }

    @TypeConverter
    fun fromMaterialType(materialType: MaterialType): String {
        return materialType.name
    }

    @TypeConverter
    fun toMaterialType(value: String): MaterialType {
        return MaterialType.valueOf(value)
    }
}