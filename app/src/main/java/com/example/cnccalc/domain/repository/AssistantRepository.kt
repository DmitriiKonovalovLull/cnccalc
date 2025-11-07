package com.example.cnccalc.domain.repository

import com.example.cnccalc.data.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface AssistantRepository {
    fun getChatHistory(): Flow<List<ChatMessage>>
    suspend fun sendMessage(message: String, context: String?): String
    suspend fun saveMessage(message: ChatMessage)
}