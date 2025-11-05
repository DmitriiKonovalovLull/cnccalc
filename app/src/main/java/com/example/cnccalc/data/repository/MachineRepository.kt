package com.example.cnccalc.data.repository

import com.example.cnccalc.data.database.MachineDao
import com.example.cnccalc.data.database.MachineEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MachineRepository(private val machineDao: MachineDao) {

    fun getAllMachines(): Flow<List<MachineEntity>> = machineDao.getAllMachines()

    fun searchMachines(query: String): Flow<List<MachineEntity>> = machineDao.searchMachines(query)

    suspend fun refreshMachinesFromApi() {
        // Временная реализация - позже заменим на реальный API
        val demoMachines = listOf(
            MachineEntity(
                id = "1",
                name = "Haas VF-2",
                manufacturer = "Haas",
                model = "VF-2",
                type = "milling",
                year = 2020,
                maxRPM = 7500,
                workingArea = "762x406x508mm",
                toolChangerCapacity = 20,
                spindlePower = 11.2,
                weight = 3628.0,
                dimensions = "2286x1829x2286mm"
            )
        )
        machineDao.insertAll(demoMachines)
    }

    suspend fun hasData(): Boolean = machineDao.getCount() > 0
}