package com.example.cnccalc.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MachineDao {
    @Query("SELECT * FROM machines")
    fun getAllMachines(): Flow<List<MachineEntity>>

    @Query("SELECT * FROM machines WHERE name LIKE '%' || :query || '%' OR model LIKE '%' || :query || '%' OR manufacturer LIKE '%' || :query || '%'")
    fun searchMachines(query: String): Flow<List<MachineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(machines: List<MachineEntity>)

    @Query("SELECT COUNT(*) FROM machines")
    suspend fun getCount(): Int
}