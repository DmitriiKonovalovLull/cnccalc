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
abstract class CNCDatabase : RoomDatabase() {
    abstract fun machineDao(): MachineDao

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