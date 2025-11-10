package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.model.Material
import com.example.cnccalc.domain.models.material.MaterialType
import kotlinx.coroutines.flow.Flow

interface MaterialRepository {
    fun getAllMaterials(): Flow<List<Material>>
    fun getMaterialsByType(type: MaterialType): Flow<List<Material>>
    suspend fun getMaterialById(id: String): Material?
    suspend fun searchMaterials(query: String): List<Material>
    suspend fun addMaterial(material: Material)
    suspend fun updateMaterial(material: Material)
    suspend fun deleteMaterial(material: Material)

    // Новые методы для поиска материалов
    suspend fun searchMaterialsOnline(query: String): List<Material>
    suspend fun recognizeMaterialFromImage(imagePath: String): Material?
}