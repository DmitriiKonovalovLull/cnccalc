package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.models.Machine
import kotlinx.coroutines.flow.Flow

interface MachineRepository {
    fun getAllMachines(): Flow<List<Machine>>
    suspend fun addMachine(machine: Machine)
}