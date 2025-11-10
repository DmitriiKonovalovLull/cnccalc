package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.data.model.ChatMessage
import com.example.cnccalc.domain.repository.AssistantRepository
import javax.inject.Inject

class ProcessQuestionUseCase @Inject constructor(
    private val assistantRepository: AssistantRepository
) {
    suspend operator fun invoke(question: String, context: String): ChatMessage {
        return assistantRepository.sendMessage(question, context)
    }
}