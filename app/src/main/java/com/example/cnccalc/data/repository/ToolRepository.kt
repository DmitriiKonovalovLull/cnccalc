package com.example.cnccalc.data.repository

import androidx.lifecycle.LiveData
import com.example.cnccalc.data.db.ToolDao
import com.example.cnccalc.data.db.ToolEntity

class ToolRepository(private val dao: ToolDao) {

    fun getAllTools(): LiveData<List<ToolEntity>> = dao.getAllTools()

    suspend fun insertTool(tool: ToolEntity): Long = dao.insert(tool)

    suspend fun deleteTool(tool: ToolEntity): Int = dao.delete(tool)

    fun findToolByName(name: String): LiveData<List<ToolEntity>> = dao.findByName(name)

    fun getToolsByType(type: String): LiveData<List<ToolEntity>> = dao.getToolsByType(type)
}
