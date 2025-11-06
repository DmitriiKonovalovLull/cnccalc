package com.example.cnccalc.ai.gcode

class GCodeGenerator {

    fun generateGCode(parameters: Map<String, Any>): String {
        val sb = StringBuilder()

        // Basic G-code template
        sb.appendLine("G21") // Millimeter units
        sb.appendLine("G90") // Absolute positioning
        sb.appendLine("G17") // XY plane selection

        // TODO: Implement actual G-code generation based on parameters
        val toolDiameter = parameters["toolDiameter"] as? Float ?: 6.0f
        val depth = parameters["depth"] as? Float ?: 2.0f

        sb.appendLine("M3 S${parameters["rpm"] ?: 10000}") // Spindle start
        sb.appendLine("G0 X0 Y0 Z5") // Rapid to safe height
        sb.appendLine("G1 Z-${depth} F${parameters["plungeRate"] ?: 100}") // Plunge
        sb.appendLine("G1 X10 F${parameters["feedRate"] ?: 500}") // Feed move

        sb.appendLine("M5") // Spindle stop
        sb.appendLine("G0 Z50") // Return to safe height
        sb.appendLine("M30") // Program end

        return sb.toString()
    }
}