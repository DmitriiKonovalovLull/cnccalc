package com.example.cnccalc.data.repository

import com.example.cnccalc.data.local.dao.MachineDao
import com.example.cnccalc.data.model.Machine
import com.example.cnccalc.domain.repository.MachineRepository
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

    override fun getMachinesByType(type: String): Flow<List<Machine>> {
        return machineDao.getMachinesByType(type).map { entities ->
            entities.map { it.toMachine() }
        }
    }

    override suspend fun getMachineById(id: String): Machine? {
        return machineDao.getMachineById(id)?.toMachine()
    }

    override suspend fun addMachine(machine: Machine) {
        machineDao.insertMachine(machine.toEntity())
    }

    override suspend fun updateMachine(machine: Machine) {
        machineDao.updateMachine(machine.toEntity())
    }

    override suspend fun deleteMachine(machine: Machine) {
        machineDao.deleteMachine(machine.toEntity())
    }
}

private fun Machine.toEntity(): com.example.cnccalc.data.local.entities.MachineEntity {
    return com.example.cnccalc.data.local.entities.MachineEntity(
        id = id,
        name = name,
        type = type,
        maxRPM = maxRPM,
        power = power,
        workArea = workArea,
        description = description,
        model = model,
        manufacturer = manufacturer
    )
}