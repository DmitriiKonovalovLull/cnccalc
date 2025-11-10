package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.model.Machine
import kotlinx.coroutines.flow.Flow

interface MachineRepository {
    fun getAllMachines(): Flow<List<Machine>>
    fun getMachinesByType(type: String): Flow<List<Machine>>
    suspend fun getMachineById(id: String): Machine?
    suspend fun addMachine(machine: Machine)
    suspend fun updateMachine(machine: Machine)
    suspend fun deleteMachine(machine: Machine)

    // Новые методы для поиска и редактирования характеристик
    suspend fun searchMachinesOnline(query: String): List<Machine>
    suspend fun updateMachineCharacteristics(
        machineId: String,
        maxRPM: Int? = null,
        power: Float? = null,
        toolHolderType: String? = null,
        spindleCondition: String? = null // "new", "used", "worn"
    ): Machine

    suspend fun calculateMachineEfficiency(machineId: String): Float
    suspend fun getRecommendedMaintenance(machineId: String): String
}