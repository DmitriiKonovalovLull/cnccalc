package com.example.cnccalc.domain.repository

interface GCodeRepository {
    suspend fun generateGCode(
        toolId: String,
        materialId: String,
        machineId: String,
        operations: List<String>
    ): String

    suspend fun optimizeGCode(gcode: String): String
    suspend fun validateGCode(gcode: String): Boolean
    suspend fun saveGCodeProgram(name: String, gcode: String)
    suspend fun loadGCodeProgram(name: String): String
}