package com.example.cnccalc.data.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Добавляем новую колонку для поставщика инструментов
        database.execSQL("ALTER TABLE tools ADD COLUMN supplier TEXT DEFAULT ''")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Добавляем таблицу для избранных инструментов
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS favorite_tools (
                id TEXT PRIMARY KEY NOT NULL,
                tool_id TEXT NOT NULL,
                user_id TEXT NOT NULL,
                created_at INTEGER NOT NULL
            )
        """)
    }
}