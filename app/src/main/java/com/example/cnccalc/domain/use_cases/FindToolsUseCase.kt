package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.data.model.Tool
import com.example.cnccalc.domain.models.ToolType
import com.example.cnccalc.domain.repository.ToolRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindToolsUseCase @Inject constructor(
    private val toolRepository: ToolRepository
) {
    suspend operator fun invoke(type: ToolType? = null): Flow<List<Tool>> {
        return if (type != null) {
            toolRepository.getToolsByType(type)
        } else {
            toolRepository.getAllTools()
        }
    }
}