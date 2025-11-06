package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.domain.repository.GCodeRepository
import javax.inject.Inject

class GenerateGCodeUseCase @Inject constructor(
    private val repository: GCodeRepository
) {
    suspend operator fun invoke(parameters: Map<String, Any>): String {
        return repository.generateGCode(parameters)
    }
}