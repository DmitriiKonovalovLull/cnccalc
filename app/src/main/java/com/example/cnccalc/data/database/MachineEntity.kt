package com.example.cnccalc.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "machines")
data class MachineEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val manufacturer: String,
    val model: String,
    val type: String,
    val year: Int,
    val maxRPM: Int,
    val workingArea: String,
    val toolChangerCapacity: Int,
    val spindlePower: Double,
    val weight: Double,
    val dimensions: String,
    val imageUrl: String? = null
)