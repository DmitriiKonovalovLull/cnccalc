package com.example.cnccalc.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(
    entities = [MachineEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ToolDatabase : RoomDatabase() {
    abstract fun machineDao(): MachineDao

    companion object {
        @Volatile
        private var INSTANCE: ToolDatabase? = null

        fun getInstance(context: Context): ToolDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToolDatabase::class.java,
                    "tool_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}