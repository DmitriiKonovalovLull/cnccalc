package com.example.cnccalc.data.repository

import com.example.cnccalc.data.local.dao.ToolDao
import com.example.cnccalc.data.model.Tool
import com.example.cnccalc.domain.models.ToolType
import com.example.cnccalc.domain.repository.ToolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ToolRepositoryImpl @Inject constructor(
    private val toolDao: ToolDao
) : ToolRepository {

    override fun getAllTools(): Flow<List<Tool>> {
        return toolDao.getAllTools().map { entities ->
            entities.map { it.toTool() }
        }
    }

    override fun getToolsByType(type: ToolType): Flow<List<Tool>> {
        return toolDao.getToolsByType(type.name).map { entities ->
            entities.map { it.toTool() }
        }
    }

    override suspend fun getToolById(id: String): Tool? {
        return toolDao.getToolById(id)?.toTool()
    }

    override suspend fun addTool(tool: Tool) {
        toolDao.insertTool(tool.toEntity())
    }

    override suspend fun updateTool(tool: Tool) {
        toolDao.updateTool(tool.toEntity())
    }

    override suspend fun deleteTool(tool: Tool) {
        toolDao.deleteTool(tool.toEntity())
    }

    override suspend fun recognizeToolFromImage(imagePath: String): Tool? {
        // TODO: Реализовать распознавание инструмента с камеры
        return null
    }

    override suspend fun searchToolsOnline(query: String): List<Tool> {
        // TODO: Реализовать поиск инструментов онлайн
        return emptyList()
    }

    override suspend fun downloadToolCatalog(category: String): List<Tool> {
        // TODO: Реализовать загрузку каталога инструментов
        return emptyList()
    }
}

private fun Tool.toEntity(): com.example.cnccalc.data.local.entities.ToolEntity {
    return com.example.cnccalc.data.local.entities.ToolEntity(
        id = id,
        name = name,
        type = type.name,
        diameter = diameter,
        flutes = flutes,
        material = material,
        description = description,
        cuttingSpeed = cuttingSpeed,
        feedPerTooth = feedPerTooth,
        imageUrl = imageUrl
    )
}