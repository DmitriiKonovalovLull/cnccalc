package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.data.model.Tool
import com.example.cnccalc.domain.repository.ToolRepository
import javax.inject.Inject

class RecognizeToolFromImageUseCase @Inject constructor(
    private val toolRepository: ToolRepository
) {
    suspend operator fun invoke(imagePath: String): Tool? {
        return toolRepository.recognizeToolFromImage(imagePath)
    }
}