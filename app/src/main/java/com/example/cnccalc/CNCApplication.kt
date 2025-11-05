package com.example.cnccalc

import android.app.Application
import com.example.cnccalc.data.database.ToolDatabase
import com.example.cnccalc.data.repository.MachineRepository

class CNCApplication : Application() {

    val database by lazy { ToolDatabase.getInstance(this) }
    val machineRepository by lazy { MachineRepository(database.machineDao()) }

    override fun onCreate() {
        super.onCreate()
    }
}