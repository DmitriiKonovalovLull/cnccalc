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
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CNCDatabase {
        return CNCDatabase.getDatabase(context)
    }
}