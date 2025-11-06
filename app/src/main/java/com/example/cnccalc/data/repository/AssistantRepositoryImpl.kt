package com.example.cnccalc.data.repository

import com.example.cnccalc.data.local.dao.ChatHistoryDao
import com.example.cnccalc.domain.repository.AssistantRepository
import com.example.cnccalc.data.models.ChatMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AssistantRepositoryImpl @Inject constructor(
    private val chatHistoryDao: ChatHistoryDao
) : AssistantRepository {

    override fun getChatHistory(): Flow<List<ChatMessage>> {
        return chatHistoryDao.getChatHistory().map { entities ->
            entities.map { entity ->
                ChatMessage(
                    id = entity.id,
                    message = entity.message,
                    isUser = entity.isUser,
                    timestamp = entity.timestamp
                )
            }
        }
    }

    override suspend fun sendMessage(message: String, context: String?): String {
        // TODO: Implement AI response logic
        return "This is a simulated response for: $message"
    }

    override suspend fun saveMessage(message: ChatMessage) {
        chatHistoryDao.insertMessage(
            com.example.cnccalc.data.local.entities.ChatHistoryEntity(
                id = message.id,
                message = message.message,
                isUser = message.isUser,
                timestamp = message.timestamp,
                context = null
            )
        )
    }
}