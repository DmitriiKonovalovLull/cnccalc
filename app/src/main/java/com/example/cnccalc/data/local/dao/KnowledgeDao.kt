package com.example.cnccalc.data.local.dao

import androidx.room.*
import com.example.cnccalc.data.local.entities.KnowledgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KnowledgeDao {

    @Query("SELECT * FROM knowledge_base")
    fun getAllKnowledge(): Flow<List<KnowledgeEntity>>

    @Query("SELECT * FROM knowledge_base WHERE id = :id")
    suspend fun getKnowledgeById(id: String): KnowledgeEntity?

    @Query("SELECT * FROM knowledge_base WHERE category = :category")
    fun getKnowledgeByCategory(category: String): Flow<List<KnowledgeEntity>>

    @Query("SELECT * FROM knowledge_base WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' OR tags LIKE '%' || :query || '%'")
    fun searchKnowledge(query: String): Flow<List<KnowledgeEntity>>

    @Query("SELECT * FROM knowledge_base WHERE relevance >= :minRelevance")
    fun getKnowledgeByMinRelevance(minRelevance: Int): Flow<List<KnowledgeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKnowledge(knowledge: KnowledgeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKnowledge(knowledgeList: List<KnowledgeEntity>)

    @Update
    suspend fun updateKnowledge(knowledge: KnowledgeEntity)

    @Delete
    suspend fun deleteKnowledge(knowledge: KnowledgeEntity)

    @Query("DELETE FROM knowledge_base WHERE category = :category")
    suspend fun deleteKnowledgeByCategory(category: String)
}