package com.example.cnccalc.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.cnccalc.data.local.dao.*
import com.example.cnccalc.data.local.entities.*

@Database(
    entities = [
        ToolEntity::class,
        MachineEntity::class,
        MaterialEntity::class,
        OperationEntity::class,
        ChatHistoryEntity::class,
        KnowledgeEntity::class
    ],
    version = 1, // Текущая версия
    exportSchema = true
)
abstract class CNCDatabase : RoomDatabase() {

    abstract fun toolDao(): ToolDao
    abstract fun machineDao(): MachineDao
    abstract fun materialDao(): MaterialDao
    abstract fun operationDao(): OperationDao
    abstract fun chatHistoryDao(): ChatHistoryDao
    abstract fun knowledgeDao(): KnowledgeDao

    companion object {
        @Volatile
        private var INSTANCE: CNCDatabase? = null

        fun getInstance(context: Context): CNCDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CNCDatabase::class.java,
                    "cnc_database"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3) // Добавляем миграции
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}