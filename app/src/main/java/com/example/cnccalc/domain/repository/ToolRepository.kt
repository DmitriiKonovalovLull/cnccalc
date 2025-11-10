package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.model.Tool
import com.example.cnccalc.domain.models.ToolType
import kotlinx.coroutines.flow.Flow

interface ToolRepository {
    fun getAllTools(): Flow<List<Tool>>
    fun getToolsByType(type: ToolType): Flow<List<Tool>>
    suspend fun getToolById(id: String): Tool?
    suspend fun addTool(tool: Tool)
    suspend fun updateTool(tool: Tool)
    suspend fun deleteTool(tool: Tool)

    // Новые методы для камеры и интернета
    suspend fun recognizeToolFromImage(imagePath: String): Tool?
    suspend fun searchToolsOnline(query: String): List<Tool>
    suspend fun downloadToolCatalog(category: String): List<Tool>
}