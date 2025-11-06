package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.domain.models.Tool
import com.example.cnccalc.domain.repository.ToolRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindToolsUseCase @Inject constructor(
    private val repository: ToolRepository
) {
    operator fun invoke(material: String, operation: String): Flow<List<Tool>> {
        return repository.getAllTools().map { tools ->
            tools.filter { it.suitableFor(material, operation) }
        }
    }
}