package com.example.cnccalc.di

import android.content.Context
import com.example.cnccalc.data.local.database.CNCDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CNCDatabase {
        return CNCDatabase.getInstance(context)
    }

    @Provides
    fun provideToolDao(database: CNCDatabase) = database.toolDao()

    @Provides
    fun provideMachineDao(database: CNCDatabase) = database.machineDao()

    @Provides
    fun provideMaterialDao(database: CNCDatabase) = database.materialDao()

    @Provides
    fun provideOperationDao(database: CNCDatabase) = database.operationDao()

    @Provides
    fun provideChatHistoryDao(database: CNCDatabase) = database.chatHistoryDao()

    @Provides
    fun provideKnowledgeDao(database: CNCDatabase) = database.knowledgeDao()
}