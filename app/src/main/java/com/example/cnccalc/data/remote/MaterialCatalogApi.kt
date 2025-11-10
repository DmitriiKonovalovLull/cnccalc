package com.example.cnccalc.data.remote

import com.example.cnccalc.data.model.Material

interface MaterialCatalogApi {
    suspend fun searchMaterials(query: String): List<Material>
    suspend fun getMaterialDetails(materialId: String): Material?
}