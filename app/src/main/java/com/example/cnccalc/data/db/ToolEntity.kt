package com.example.cnccalc.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tools")
data class ToolEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val type: String,
    val diameter: Double? = null,
    val length: Double? = null,
    val params: String? = null,
    val photoUri: String? = null
)
