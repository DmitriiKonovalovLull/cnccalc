package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_history")
data class ChatHistoryEntity(
    @PrimaryKey
    val id: String,
    val message: String,
    val isUser: Boolean,
    val timestamp: Long,
    val context: String? = null
)