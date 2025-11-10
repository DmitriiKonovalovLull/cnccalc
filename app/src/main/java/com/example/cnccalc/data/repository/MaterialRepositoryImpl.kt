package com.example.cnccalc.data.repository

import com.example.cnccalc.data.local.dao.MaterialDao
import com.example.cnccalc.data.model.Material
import com.example.cnccalc.domain.models.material.MaterialType
import com.example.cnccalc.domain.repository.MaterialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MaterialRepositoryImpl @Inject constructor(
    private val materialDao: MaterialDao
) : MaterialRepository {

    override fun getAllMaterials(): Flow<List<Material>> {
        return materialDao.getAllMaterials().map { entities ->
            entities.map { it.toDomainMaterial() }
        }
    }

    override fun getMaterialsByType(type: MaterialType): Flow<List<Material>> {
        return materialDao.getMaterialsByType(type.name).map { entities ->
            entities.map { it.toDomainMaterial() }
        }
    }

    override suspend fun getMaterialById(id: String): Material? {
        return materialDao.getMaterialById(id)?.toDomainMaterial()
    }

    override suspend fun searchMaterials(query: String): List<Material> {
        return materialDao.searchMaterials(query).first().map { it.toDomainMaterial() }
    }

    override suspend fun addMaterial(material: Material) {
        materialDao.insertMaterial(material.toEntity())
    }

    override suspend fun updateMaterial(material: Material) {
        materialDao.updateMaterial(material.toEntity())
    }

    override suspend fun deleteMaterial(material: Material) {
        materialDao.deleteMaterial(material.toEntity())
    }

    override suspend fun searchMaterialsOnline(query: String): List<Material> {
        return emptyList()
    }

    override suspend fun recognizeMaterialFromImage(imagePath: String): Material? {
        return null
    }
}

// Переименовываем функцию чтобы избежать конфликта с Entity
private fun com.example.cnccalc.data.local.entities.MaterialEntity.toDomainMaterial(): Material {
    return Material(
        id = id,
        name = name,
        type = type,
        hardness = hardness,
        tensileStrength = tensileStrength,
        recommendedCuttingSpeed = recommendedCuttingSpeed,
        recommendedFeedRate = recommendedFeedRate,
        description = description,
        imageUrl = imageUrl
    )
}

private fun Material.toEntity(): com.example.cnccalc.data.local.entities.MaterialEntity {
    return com.example.cnccalc.data.local.entities.MaterialEntity(
        id = id,
        name = name,
        type = type,
        hardness = hardness,
        tensileStrength = tensileStrength,
        recommendedCuttingSpeed = recommendedCuttingSpeed,
        recommendedFeedRate = recommendedFeedRate,
        description = description,
        imageUrl = imageUrl
    )
}