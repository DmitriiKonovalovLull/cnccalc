package com.example.cnccalc.data.local.dao

import androidx.room.*
import com.example.cnccalc.data.local.entities.MaterialEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialDao {

    @Query("SELECT * FROM materials")
    fun getAllMaterials(): Flow<List<MaterialEntity>>

    @Query("SELECT * FROM materials WHERE id = :id")
    suspend fun getMaterialById(id: String): MaterialEntity?

    @Query("SELECT * FROM materials WHERE type = :type")
    fun getMaterialsByType(type: String): Flow<List<MaterialEntity>>

    @Query("SELECT * FROM materials WHERE hardness BETWEEN :minHardness AND :maxHardness")
    fun getMaterialsByHardnessRange(minHardness: Float, maxHardness: Float): Flow<List<MaterialEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaterial(material: MaterialEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMaterials(materials: List<MaterialEntity>)

    @Update
    suspend fun updateMaterial(material: MaterialEntity)

    @Delete
    suspend fun deleteMaterial(material: MaterialEntity)

    @Query("SELECT * FROM materials WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchMaterials(query: String): Flow<List<MaterialEntity>>
}
