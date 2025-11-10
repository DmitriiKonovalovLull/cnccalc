package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class OperationEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String, // milling, drilling, turning, etc.
    val description: String,
    val toolId: String, // ID инструмента
    val materialId: String, // ID материала
    val machineId: String, // ID станка
    val cuttingSpeed: Float,
    val feedRate: Float,
    val depthOfCut: Float,
    val widthOfCut: Float,
    val createdAt: Long,
    val resultGCode: String? = null
)