package com.example.cnccalc.data.local.dao

import androidx.room.*
import com.example.cnccalc.data.local.entities.ChatHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatHistoryDao {

    @Query("SELECT * FROM chat_history ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<ChatHistoryEntity>>

    @Query("SELECT * FROM chat_history WHERE context = :context ORDER BY timestamp ASC")
    fun getMessagesByContext(context: String): Flow<List<ChatHistoryEntity>>

    @Query("SELECT * FROM chat_history WHERE timestamp >= :sinceTimestamp ORDER BY timestamp ASC")
    fun getMessagesSince(sinceTimestamp: Long): Flow<List<ChatHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatHistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMessages(messages: List<ChatHistoryEntity>)

    @Update
    suspend fun updateMessage(message: ChatHistoryEntity)

    @Delete
    suspend fun deleteMessage(message: ChatHistoryEntity)

    @Query("DELETE FROM chat_history WHERE context = :context")
    suspend fun deleteMessagesByContext(context: String)

    @Query("DELETE FROM chat_history WHERE timestamp < :beforeTimestamp")
    suspend fun deleteMessagesOlderThan(beforeTimestamp: Long)
}