package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.domain.repository.GCodeRepository
import javax.inject.Inject

class GenerateGCodeUseCase @Inject constructor(
    private val gCodeRepository: GCodeRepository
) {
    suspend operator fun invoke(
        toolId: String,
        materialId: String,
        machineId: String,
        operations: List<String>
    ): String {
        return gCodeRepository.generateGCode(toolId, materialId, machineId, operations)
    }
}