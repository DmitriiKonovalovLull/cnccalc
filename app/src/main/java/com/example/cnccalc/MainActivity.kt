package com.example.cnccalc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private lateinit var pythonService: PythonService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация Python-сервиса
        pythonService = PythonService(this)

        setContent {
            val (resultText, setResultText) = remember { mutableStateOf("") }

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "AI-ассистент ЧПУ",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        Button(onClick = {
                            // Тестовый вызов Python
                            try {
                                val testResult = pythonService.testPython()
                                setResultText(testResult)
                                Log.d("PythonTest", testResult)
                            } catch (e: Exception) {
                                setResultText("Ошибка: ${e.message}")
                                Log.e("PythonTest", "Ошибка Python", e)
                            }
                        }) {
                            Text("Проверить Python")
                        }

                        // Показ результата
                        if (resultText.isNotEmpty()) {
                            Text(
                                text = resultText,
                                modifier = Modifier.padding(top = 16.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}