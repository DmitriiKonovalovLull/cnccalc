package com.example.cnccalc.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "knowledge_base")
data class KnowledgeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val category: String,
    val tags: String, // JSON массив тегов
    val relevance: Int, // Релевантность (1-10)
    val createdAt: Long,
    val updatedAt: Long
)