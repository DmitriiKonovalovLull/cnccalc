package com.example.cnccalc.data.remote

import com.example.cnccalc.data.model.Tool

interface ToolCatalogApi {
    suspend fun searchTools(query: String): List<Tool>
    suspend fun getCatalog(category: String): List<Tool>
    suspend fun getToolDetails(toolId: String): Tool?
}