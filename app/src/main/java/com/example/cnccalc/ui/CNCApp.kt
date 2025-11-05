package com.example.cnccalc.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cnccalc.ui.screens.CalculatorScreen
import com.example.cnccalc.ui.screens.CameraScanScreen
import com.example.cnccalc.ui.screens.HistoryScreen
import com.example.cnccalc.ui.screens.SettingsScreen
import com.example.cnccalc.ui.theme.CNCCalcTheme

@Composable
fun CNCApp() {
    CNCNavigation()
}

@Composable
fun CNCNavigation() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Calculator) }

    when (currentScreen) {
        Screen.Calculator -> {
            CalculatorScreen(
                onBackClick = { /* Не нужно для главного экрана */ },
                onHistoryClick = { currentScreen = Screen.History }
            )
        }
        Screen.Camera -> {
            CameraScanScreen(
                onBackClick = { currentScreen = Screen.Calculator }
            )
        }
        Screen.History -> {
            HistoryScreen(
                onBackClick = { currentScreen = Screen.Calculator }
            )
        }
        Screen.Settings -> {
            SettingsScreen(
                onBackClick = { currentScreen = Screen.Calculator }
            )
        }
    }
}

sealed class Screen {
    object Calculator : Screen()
    object Camera : Screen()
    object History : Screen()
    object Settings : Screen()
}

@Preview(showBackground = true)
@Composable
fun PreviewCNCApp() {
    CNCCalcTheme {
        CNCApp()
    }
}