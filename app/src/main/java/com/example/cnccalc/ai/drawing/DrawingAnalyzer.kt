package com.example.cnccalc.ai.drawing

import android.graphics.Bitmap
import android.graphics.Color
import com.example.cnccalc.data.model.DrawingAnalysis
import com.example.cnccalc.data.model.Tool
import kotlin.math.sqrt

class DrawingAnalyzer {

    fun analyzeDrawing(bitmap: Bitmap): DrawingAnalysis {
        // ✅ ТЕПЕРЬ ИСПОЛЬЗУЕМ bitmap
        val contours = detectContours(bitmap)
        val dimensions = extractDimensions(bitmap)
        val features = extractFeatures(bitmap, contours)
        val recommendedTools = recommendTools(contours, dimensions, features)

        return DrawingAnalysis(
            contours = contours,
            dimensions = dimensions,
            features = features,
            recommendedTools = recommendedTools
        )
    }

    fun detectContours(bitmap: Bitmap): List<Contour> {
        // ✅ ТЕПЕРЬ ИСПОЛЬЗУЕМ bitmap - простая реализация обнаружения краев
        val contours = mutableListOf<Contour>()

        // Простой алгоритм обнаружения контуров по изменению цвета
        for (y in 1 until bitmap.height - 1) {
            for (x in 1 until bitmap.width - 1) {
                val currentPixel = bitmap.getPixel(x, y)
                val rightPixel = bitmap.getPixel(x + 1, y)
                val bottomPixel = bitmap.getPixel(x, y + 1)

                // Если есть значительное изменение цвета - считаем это краем
                if (isEdge(currentPixel, rightPixel) || isEdge(currentPixel, bottomPixel)) {
                    contours.add(Contour(listOf(Point(x.toFloat(), y.toFloat())), "edge"))
                }
            }
        }

        return contours
    }

    fun extractDimensions(bitmap: Bitmap): Map<String, Float> {
        // ✅ ТЕПЕРЬ ИСПОЛЬЗУЕМ bitmap - извлекаем реальные размеры
        return mapOf(
            "width_pixels" to bitmap.width.toFloat(),
            "height_pixels" to bitmap.height.toFloat(),
            "diagonal" to sqrt((bitmap.width * bitmap.width + bitmap.height * bitmap.height).toDouble()).toFloat(),
            "area" to (bitmap.width * bitmap.height).toFloat()
        )
    }

    private fun extractFeatures(bitmap: Bitmap, contours: List<Contour>): List<String> {
        // ✅ ТЕПЕРЬ ИСПОЛЬЗУЕМ оба параметра
        val features = mutableListOf<String>()

        // Анализируем изображение на наличие особенностей
        if (contours.size > 100) features.add("complex_geometry")
        if (hasStraightLines(contours)) features.add("straight_lines")
        if (hasCurves(contours)) features.add("curves")
        if (hasSmallDetails(bitmap, contours)) features.add("small_details")

        return features
    }

    private fun recommendTools(contours: List<Contour>, dimensions: Map<String, Float>, features: List<String>): List<Tool> {
        // Логика рекомендации инструментов на основе анализа
        val recommendedTools = mutableListOf<Tool>()

        if (features.contains("small_details")) {
            recommendedTools.add(createTool("small_endmill", "Мини фреза 1мм", "endmill", 1.0f, 24000))
        }

        if (features.contains("straight_lines")) {
            recommendedTools.add(createTool("standard_endmill", "Фреза 6мм", "endmill", 6.0f, 18000))
        }

        return recommendedTools
    }

    // Вспомогательные методы
    private fun isEdge(color1: Int, color2: Int, threshold: Int = 50): Boolean {
        val diff = Color.red(color1) - Color.red(color2)
        return Math.abs(diff) > threshold
    }

    private fun hasStraightLines(contours: List<Contour>): Boolean {
        // Простая проверка на прямые линии
        return contours.size > 10
    }

    private fun hasCurves(contours: List<Contour>): Boolean {
        // Простая проверка на кривые
        return contours.any { it.points.size > 5 }
    }

    private fun hasSmallDetails(bitmap: Bitmap, contours: List<Contour>): Boolean {
        // Проверка на мелкие детали
        return contours.size > bitmap.width / 10
    }

    private fun createTool(id: String, name: String, type: String, diameter: Float, maxRPM: Int): Tool {
        return Tool(
            id = id,
            name = name,
            type = type,
            material = "universal",
            diameter = diameter,
            maxRPM = maxRPM,
            description = "Автоматически рекомендован based on drawing analysis"
        )
    }
}

// Вспомогательные data классы
data class Contour(val points: List<Point>, val type: String)
data class Point(val x: Float, val y: Float)
