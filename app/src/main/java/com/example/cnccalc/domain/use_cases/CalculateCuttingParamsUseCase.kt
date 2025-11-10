package com.example.cnccalc.domain.use_cases

import com.example.cnccalc.data.model.CuttingParameters
import com.example.cnccalc.data.model.Material
import com.example.cnccalc.data.model.Tool
import javax.inject.Inject

class CalculateCuttingParamsUseCase @Inject constructor() {
    operator fun invoke(tool: Tool, material: Material): CuttingParameters {
        return material.getCuttingParameters(tool.material)
    }
}