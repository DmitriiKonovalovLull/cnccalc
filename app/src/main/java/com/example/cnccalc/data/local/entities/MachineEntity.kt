package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "machines")
data class MachineEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val maxRPM: Int,
    val power: Float,
    val workArea: String,
    val description: String
) {
    fun toMachine(): com.example.cnccalc.data.model.Machine {
        return com.example.cnccalc.data.model.Machine(
            id = id,
            name = name,
            type = type,
            maxRPM = maxRPM,
            power = power,
            workArea = workArea,
            description = description
        )
    }
}

fun com.example.cnccalc.data.model.Machine.toEntity(): MachineEntity {
    return MachineEntity(
        id = id,
        name = name,
        type = type,
        maxRPM = maxRPM,
        power = power,
        workArea = workArea,
        description = description
    )
}