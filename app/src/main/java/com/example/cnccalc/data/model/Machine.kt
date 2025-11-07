package com.example.cnccalc.data.model

data class Machine(
    val id: String,
    val name: String,
    val type: String,
    val maxRPM: Int,
    val power: Float,
    val workArea: String,
    val description: String,
    val model: String,
    val manufacturer: String
)