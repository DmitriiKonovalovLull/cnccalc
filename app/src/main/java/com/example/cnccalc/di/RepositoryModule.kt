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
    abstract fun bindToolRepository(repository: ToolRepositoryImpl): ToolRepository

    @Binds
    abstract fun bindMachineRepository(repository: MachineRepositoryImpl): MachineRepository

    @Binds
    abstract fun bindDrawingRepository(repository: DrawingRepositoryImpl): DrawingRepository

    @Binds
    abstract fun bindAssistantRepository(repository: AssistantRepositoryImpl): AssistantRepository
}