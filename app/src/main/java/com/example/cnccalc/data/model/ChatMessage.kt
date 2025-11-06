package com.example.cnccalc.data.model

data class ChatMessage(
    val id: String,
    val message: String,
    val isUser: Boolean,
    val timestamp: Long
)