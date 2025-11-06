package com.example.cnccalc.domain.repository

interface GCodeRepository {
    suspend fun generateGCode(parameters: Map<String, Any>): String
    suspend fun optimizePath(path: List<Any>): List<Any>
}