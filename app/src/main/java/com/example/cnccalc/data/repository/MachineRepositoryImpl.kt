package com.example.cnccalc.data.repository

import com.example.cnccalc.data.local.dao.MachineDao
import com.example.cnccalc.domain.repository.MachineRepository
import com.example.cnccalc.data.models.Machine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MachineRepositoryImpl @Inject constructor(
    private val machineDao: MachineDao
) : MachineRepository {

    override fun getAllMachines(): Flow<List<Machine>> {
        return machineDao.getAllMachines().map { entities ->
            entities.map { it.toMachine() }
        }
    }

    override suspend fun addMachine(machine: Machine) {
        machineDao.insertMachine(machine.toEntity())
    }
}