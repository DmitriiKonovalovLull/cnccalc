package com.example.cnccalc.data.local.dao

import androidx.room.*
import com.example.cnccalc.data.local.entities.ToolEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToolDao {

    @Query("SELECT * FROM tools")
    fun getAllTools(): Flow<List<ToolEntity>>

    @Query("SELECT * FROM tools WHERE tool_id = :id")
    suspend fun getToolById(id: String): ToolEntity?

    @Query("SELECT * FROM tools WHERE type = :type")
    fun getToolsByType(type: String): Flow<List<ToolEntity>>

    @Query("SELECT * FROM tools WHERE diameter_mm BETWEEN :minDiameter AND :maxDiameter")
    fun getToolsByDiameterRange(minDiameter: Float, maxDiameter: Float): Flow<List<ToolEntity>>

    @Query("SELECT * FROM tools WHERE material = :material")
    fun getToolsByMaterial(material: String): Flow<List<ToolEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTool(tool: ToolEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTools(tools: List<ToolEntity>)

    @Update
    suspend fun updateTool(tool: ToolEntity)

    @Delete
    suspend fun deleteTool(tool: ToolEntity)

    @Query("DELETE FROM tools WHERE tool_id = :id")
    suspend fun deleteToolById(id: String)

    @Query("SELECT * FROM tools WHERE is_available = 1")
    fun getAvailableTools(): Flow<List<ToolEntity>>
}