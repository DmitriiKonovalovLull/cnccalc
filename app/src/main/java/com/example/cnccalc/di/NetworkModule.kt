package com.example.cnccalc.di

import com.example.cnccalc.data.model.Material
import com.example.cnccalc.data.model.Tool
import com.example.cnccalc.data.remote.MaterialCatalogApi
import com.example.cnccalc.data.remote.ToolCatalogApi
import com.example.cnccalc.data.remote.ToolRecognitionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideToolCatalogApi(): ToolCatalogApi {
        return object : ToolCatalogApi {
            override suspend fun searchTools(query: String): List<Tool> = emptyList()
            override suspend fun getCatalog(category: String): List<Tool> = emptyList()
            override suspend fun getToolDetails(toolId: String): Tool? = null
        }
    }

    @Provides
    @Singleton
    fun provideToolRecognitionApi(): ToolRecognitionApi {
        return object : ToolRecognitionApi {
            override suspend fun recognizeTool(image: android.graphics.Bitmap): Tool? = null
            override suspend fun analyzeToolImage(imagePath: String): Tool? = null
        }
    }

    @Provides
    @Singleton
    fun provideMaterialCatalogApi(): MaterialCatalogApi {
        return object : MaterialCatalogApi {
            override suspend fun searchMaterials(query: String): List<Material> = emptyList()
            override suspend fun getMaterialDetails(materialId: String): Material? = null
        }
    }
}