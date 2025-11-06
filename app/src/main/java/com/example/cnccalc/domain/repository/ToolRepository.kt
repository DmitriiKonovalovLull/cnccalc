package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.models.Tool
import com.example.cnccalc.domain.models.ToolType
import kotlinx.coroutines.flow.Flow

interface ToolRepository {
    fun getAllTools(): Flow<List<Tool>>
    fun getToolsByType(type: ToolType): Flow<List<Tool>>
    suspend fun addTool(tool: Tool)
    suspend fun deleteTool(tool: Tool)
}