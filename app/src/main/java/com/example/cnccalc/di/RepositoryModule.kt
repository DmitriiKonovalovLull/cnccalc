package com.example.cnccalc.di

import com.example.cnccalc.data.repository.*
import com.example.cnccalc.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindToolRepository(impl: ToolRepositoryImpl): ToolRepository

    @Binds
    abstract fun bindMachineRepository(impl: MachineRepositoryImpl): MachineRepository

    @Binds
    abstract fun bindMaterialRepository(impl: MaterialRepositoryImpl): MaterialRepository

    @Binds
    abstract fun bindDrawingRepository(impl: DrawingRepositoryImpl): DrawingRepository

    @Binds
    abstract fun bindAssistantRepository(impl: AssistantRepositoryImpl): AssistantRepository

    @Binds
    abstract fun bindGCodeRepository(impl: GCodeRepositoryImpl): GCodeRepository
}