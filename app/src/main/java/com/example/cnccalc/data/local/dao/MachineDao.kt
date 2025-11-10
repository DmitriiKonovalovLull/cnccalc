package com.example.cnccalc.data.local.dao

import androidx.room.*
import com.example.cnccalc.data.local.entities.MachineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MachineDao {

    @Query("SELECT * FROM machines")
    fun getAllMachines(): Flow<List<MachineEntity>>

    @Query("SELECT * FROM machines WHERE id = :id")
    suspend fun getMachineById(id: String): MachineEntity?

    @Query("SELECT * FROM machines WHERE type = :type")
    fun getMachinesByType(type: String): Flow<List<MachineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMachine(machine: MachineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMachines(machines: List<MachineEntity>)

    @Update
    suspend fun updateMachine(machine: MachineEntity)

    @Delete
    suspend fun deleteMachine(machine: MachineEntity)

    @Query("SELECT * FROM machines WHERE maxRPM >= :minRPM")
    fun getMachinesWithMinRPM(minRPM: Int): Flow<List<MachineEntity>>
}