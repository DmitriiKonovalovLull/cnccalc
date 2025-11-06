package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "operations")
data class OperationEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val toolId: String,
    val materialId: String,
    val parameters: String,
    val timestamp: Long
)