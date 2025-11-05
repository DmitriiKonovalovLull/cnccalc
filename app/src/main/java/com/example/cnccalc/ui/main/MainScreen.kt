package com.example.cnccalc.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)   // 👈 добавили
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("CNC Calc") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Добро пожаловать!", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
