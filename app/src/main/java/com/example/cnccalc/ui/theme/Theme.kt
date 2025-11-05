package com.example.cnccalc.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF6750A4),
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = androidx.compose.ui.graphics.Color(0xFF625B71),
    onSecondary = androidx.compose.ui.graphics.Color.White,
    background = androidx.compose.ui.graphics.Color(0xFFFFFBFE),
    onBackground = androidx.compose.ui.graphics.Color(0xFF1C1B1F)
)

private val DarkColors = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFFD0BCFF),
    onPrimary = androidx.compose.ui.graphics.Color(0xFF371E73)
)

@Composable
fun CNCCalcTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = androidx.compose.material3.Typography(), // можно заменить своей типографикой
        shapes = androidx.compose.material3.Shapes(), // можно заменить своими shape
        content = content
    )
}
