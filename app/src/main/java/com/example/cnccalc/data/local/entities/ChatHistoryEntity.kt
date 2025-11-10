package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_history")
data class ChatHistoryEntity(
    @PrimaryKey
    val id: String,
    val message: String,
    val isUserMessage: Boolean,
    val timestamp: Long,
    val context: String, // Контекст разговора (tool_selection, gcode_help, etc.)
    val attachments: String? = null // JSON с вложениями
)