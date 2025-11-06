package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.domain.repository.AssistantRepository
import javax.inject.Inject

class ProcessQuestionUseCase @Inject constructor(
    private val repository: AssistantRepository
) {
    suspend operator fun invoke(question: String, context: String? = null): String {
        return repository.sendMessage(question, context)
    }
}