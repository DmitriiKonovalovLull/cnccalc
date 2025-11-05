package com.example.cnccalc.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToolDao {

    @Query("SELECT * FROM tools ORDER BY id DESC")
    fun getAllTools(): LiveData<List<ToolEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tool: ToolEntity): Long

    @Delete
    suspend fun delete(tool: ToolEntity): Int

    @Query("SELECT * FROM tools WHERE name LIKE '%' || :name || '%' ORDER BY id DESC")
    fun findByName(name: String): LiveData<List<ToolEntity>>

    @Query("SELECT * FROM tools WHERE type = :type ORDER BY id DESC")
    fun getToolsByType(type: String): LiveData<List<ToolEntity>>
}
