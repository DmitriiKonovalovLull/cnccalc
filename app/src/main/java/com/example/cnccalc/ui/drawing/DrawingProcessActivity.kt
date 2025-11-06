package com.example.cnccalc.ui.drawing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.cnccalc.ui.theme.CNCCalcTheme

class DrawingProcessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CNCCalcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DrawingProcessScreen()
                }
            }
        }
    }
}

@Composable
fun DrawingProcessScreen() {
    // TODO: Реализовать экран анализа чертежей
    androidx.compose.material3.Text("Анализ чертежей - в разработке")
}