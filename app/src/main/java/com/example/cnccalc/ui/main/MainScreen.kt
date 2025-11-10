package com.example.cnccalc.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cnccalc.ui.theme.CNCCalcTheme

@Composable
fun MainScreen() {
    Text("CNC Calculator - Главный экран")
}

@Preview
@Composable
fun PreviewMainScreen() {
    CNCCalcTheme {
        MainScreen()
    }
}