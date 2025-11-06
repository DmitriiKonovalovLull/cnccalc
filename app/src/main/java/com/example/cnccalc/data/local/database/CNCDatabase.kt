package com.example.cnccalc.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.example.cnccalc.data.local.dao.*
import com.example.cnccalc.data.local.entities.*

@Database(
    entities = [
        ToolEntity::class,
        MachineEntity::class,
        MaterialEntity::class,
        OperationEntity::class,
        ChatHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class) // Добавляем конвертеры
abstract class CNCDatabase : RoomDatabase() {

    abstract fun toolDao(): ToolDao
    abstract fun machineDao(): MachineDao
    abstract fun materialDao(): MaterialDao
    abstract fun operationDao(): OperationDao
    abstract fun chatHistoryDao(): ChatHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: CNCDatabase? = null

        fun getDatabase(context: Context): CNCDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CNCDatabase::class.java,
                    "cnc_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}