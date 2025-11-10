package com.example.cnccalc.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Здесь будут провайдеры для общих компонентов приложения
}