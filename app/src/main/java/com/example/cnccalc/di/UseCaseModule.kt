package com.example.cnccalc.di

import com.example.cnccalc.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFindToolsUseCase(useCase: FindToolsUseCase): FindToolsUseCase = useCase

    @Provides
    @Singleton
    fun provideRecognizeToolFromImageUseCase(useCase: RecognizeToolFromImageUseCase): RecognizeToolFromImageUseCase = useCase

    @Provides
    @Singleton
    fun provideSearchToolsOnlineUseCase(useCase: SearchToolsOnlineUseCase): SearchToolsOnlineUseCase = useCase

    @Provides
    @Singleton
    fun provideAnalyzeDrawingUseCase(useCase: AnalyzeDrawingUseCase): AnalyzeDrawingUseCase = useCase

    @Provides
    @Singleton
    fun provideCalculateCuttingParamsUseCase(useCase: CalculateCuttingParamsUseCase): CalculateCuttingParamsUseCase = useCase

    @Provides
    @Singleton
    fun provideProcessQuestionUseCase(useCase: ProcessQuestionUseCase): ProcessQuestionUseCase = useCase

    @Provides
    @Singleton
    fun provideGenerateGCodeUseCase(useCase: GenerateGCodeUseCase): GenerateGCodeUseCase = useCase
}