package com.example.cnccalc.data.local.dao

import androidx.room.*
import com.example.cnccalc.data.local.entities.ChatHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatHistoryDao {

    @Query("SELECT * FROM chat_history ORDER BY timestamp ASC")
    fun getChatHistory(): Flow<List<ChatHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatHistoryEntity)

    @Query("DELETE FROM chat_history")
    suspend fun clearHistory()
}