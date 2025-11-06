package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.domain.models.CuttingParameters
import javax.inject.Inject

class CalculateCuttingParamsUseCase @Inject constructor() {

    operator fun invoke(
        toolDiameter: Float,
        flutes: Int,
        cuttingSpeed: Float,
        feedPerTooth: Float,
        machineMaxRPM: Int
    ): CuttingParameters {
        val rpm = calculateRPM(cuttingSpeed, toolDiameter, machineMaxRPM)
        val feedRate = calculateFeedRate(rpm, flutes, feedPerTooth)

        return CuttingParameters(
            rpm = rpm,
            feedRate = feedRate,
            cuttingSpeed = cuttingSpeed
        )
    }

    private fun calculateRPM(cuttingSpeed: Float, diameter: Float, maxRPM: Int): Int {
        val rpm = (cuttingSpeed * 1000) / (Math.PI * diameter)
        return rpm.toInt().coerceAtMost(maxRPM)
    }

    private fun calculateFeedRate(rpm: Int, flutes: Int, feedPerTooth: Float): Float {
        return rpm * flutes * feedPerTooth
    }
}