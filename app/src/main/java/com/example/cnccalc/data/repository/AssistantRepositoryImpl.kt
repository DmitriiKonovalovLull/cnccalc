package com.example.cnccalc.data.repository

import com.example.cnccalc.data.local.dao.ChatHistoryDao
import com.example.cnccalc.data.model.ChatMessage
import com.example.cnccalc.domain.repository.AssistantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AssistantRepositoryImpl @Inject constructor(
    private val chatHistoryDao: ChatHistoryDao
) : AssistantRepository {

    override fun getChatHistory(context: String): Flow<List<ChatMessage>> {
        return chatHistoryDao.getMessagesByContext(context).map { entities ->
            entities.map { it.toChatMessage() }
        }
    }

    override suspend fun sendMessage(message: String, context: String): ChatMessage {
        // TODO: Реализовать отправку сообщения AI ассистенту
        val response = "Ответ от AI ассистента на: $message"

        val userMessage = ChatMessage(
            id = System.currentTimeMillis().toString(),
            message = message,
            isUserMessage = true,
            timestamp = System.currentTimeMillis(),
            context = context
        )

        val assistantMessage = ChatMessage(
            id = (System.currentTimeMillis() + 1).toString(),
            message = response,
            isUserMessage = false,
            timestamp = System.currentTimeMillis() + 1,
            context = context
        )

        saveMessage(userMessage)
        saveMessage(assistantMessage)

        return assistantMessage
    }

    override suspend fun saveMessage(message: ChatMessage) {
        chatHistoryDao.insertMessage(message.toEntity())
    }

    override suspend fun clearChatHistory(context: String) {
        chatHistoryDao.deleteMessagesByContext(context)
    }

    override suspend fun getKnowledgeAnswer(question: String): String {
        // TODO: Реализовать поиск в базе знаний
        return "Ответ из базы знаний на вопрос: $question"
    }
}

// Добавляем функцию конвертации
private fun com.example.cnccalc.data.local.entities.ChatHistoryEntity.toChatMessage(): ChatMessage {
    return ChatMessage(
        id = id,
        message = message,
        isUserMessage = isUserMessage,
        timestamp = timestamp,
        context = context,
        attachments = attachments
    )
}

private fun ChatMessage.toEntity(): com.example.cnccalc.data.local.entities.ChatHistoryEntity {
    return com.example.cnccalc.data.local.entities.ChatHistoryEntity(
        id = id,
        message = message,
        isUserMessage = isUserMessage,
        timestamp = timestamp,
        context = context,
        attachments = attachments
    )
}