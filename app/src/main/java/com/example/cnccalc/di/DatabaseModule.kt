package com.example.cnccalc.di

import com.example.cnccalc.data.local.database.CNCDatabase
import com.example.cnccalc.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideToolDao(database: CNCDatabase): ToolDao = database.toolDao()

    @Provides
    @Singleton
    fun provideMachineDao(database: CNCDatabase): MachineDao = database.machineDao()

    @Provides
    @Singleton
    fun provideMaterialDao(database: CNCDatabase): MaterialDao = database.materialDao()

    @Provides
    @Singleton
    fun provideOperationDao(database: CNCDatabase): OperationDao = database.operationDao()

    @Provides
    @Singleton
    fun provideChatHistoryDao(database: CNCDatabase): ChatHistoryDao = database.chatHistoryDao()
}