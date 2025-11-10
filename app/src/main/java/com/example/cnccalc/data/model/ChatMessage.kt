package com.example.cnccalc.data.model

data class ChatMessage(
    val id: String,
    val message: String,
    val isUserMessage: Boolean,
    val timestamp: Long,
    val context: String,
    val attachments: String? = null
) {
    fun isRelatedToToolSelection(): Boolean {
        return context.contains("tool", ignoreCase = true) ||
                message.contains("фреза", ignoreCase = true) ||
                message.contains("сверло", ignoreCase = true) ||
                message.contains("tool", ignoreCase = true) ||
                message.contains("cutter", ignoreCase = true)
    }

    fun hasAttachments(): Boolean {
        return !attachments.isNullOrEmpty()
    }
}