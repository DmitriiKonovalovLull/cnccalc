package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface AssistantRepository {
    fun getChatHistory(context: String): Flow<List<ChatMessage>>
    suspend fun sendMessage(message: String, context: String): ChatMessage
    suspend fun saveMessage(message: ChatMessage)
    suspend fun clearChatHistory(context: String)
    suspend fun getKnowledgeAnswer(question: String): String
}