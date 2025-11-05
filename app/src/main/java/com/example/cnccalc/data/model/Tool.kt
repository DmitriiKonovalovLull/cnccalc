package com.example.cnccalc.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tools")
data class Tool(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,          // название инструмента
    val type: String,          // фреза, резец, сверло
    val photoUri: String?,     // фото (URI в хранилище телефона)
    val params: String?        // параметры (можно JSON или строку)
)