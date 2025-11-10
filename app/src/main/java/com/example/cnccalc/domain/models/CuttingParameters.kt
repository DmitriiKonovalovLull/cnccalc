package com.example.cnccalc.domain.models

data class CuttingParameters(
    val cuttingSpeed: Float,    // Скорость резания (м/мин)
    val feedRate: Float,        // Подача (мм/об или мм/зуб)
    val rpm: Int,               // Обороты шпинделя (об/мин)
    val depthOfCut: Float,      // Глубина резания (мм)
    val widthOfCut: Float       // Ширина резания (мм)
) {
    fun calculateMachiningTime(distance: Float): Float {
        return distance / feedRate
    }

    fun calculateMaterialRemovalRate(): Float {
        return depthOfCut * widthOfCut * feedRate
    }
}