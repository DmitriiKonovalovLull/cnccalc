package com.example.cnccalc.data.local.dao

import androidx.room.*
import com.example.cnccalc.data.local.entities.OperationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {

    @Query("SELECT * FROM operations")
    fun getAllOperations(): Flow<List<OperationEntity>>

    @Query("SELECT * FROM operations WHERE id = :id")
    suspend fun getOperationById(id: String): OperationEntity?

    @Query("SELECT * FROM operations WHERE type = :type")
    fun getOperationsByType(type: String): Flow<List<OperationEntity>>

    @Query("SELECT * FROM operations WHERE toolId = :toolId")
    fun getOperationsByToolId(toolId: String): Flow<List<OperationEntity>>

    @Query("SELECT * FROM operations WHERE materialId = :materialId")
    fun getOperationsByMaterialId(materialId: String): Flow<List<OperationEntity>>

    @Query("SELECT * FROM operations WHERE machineId = :machineId")
    fun getOperationsByMachineId(machineId: String): Flow<List<OperationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperation(operation: OperationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOperations(operations: List<OperationEntity>)

    @Update
    suspend fun updateOperation(operation: OperationEntity)

    @Delete
    suspend fun deleteOperation(operation: OperationEntity)

    @Query("DELETE FROM operations WHERE createdAt < :beforeTimestamp")
    suspend fun deleteOperationsOlderThan(beforeTimestamp: Long)
}