package com.example.cnccalc.data.repository

import com.example.cnccalc.data.local.dao.ToolDao
import com.example.cnccalc.domain.models.ToolType
import com.example.cnccalc.domain.repository.ToolRepository
import com.example.cnccalc.data.models.Tool
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

    override suspend fun addTool(tool: Tool) {
        toolDao.insertTool(tool.toEntity())
    }

    override suspend fun deleteTool(tool: Tool) {
        toolDao.deleteTool(tool.toEntity())
    }
}