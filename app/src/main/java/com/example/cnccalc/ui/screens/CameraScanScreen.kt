package com.example.cnccalc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cnccalc.ui.theme.CNCCalcTheme
import com.example.cnccalc.ui.viewmodel.mainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScanScreen(
    onBackClick: () -> Unit,
    viewModel: com.example.cnccalc.ui.viewmodel.MainViewModel = mainViewModel()
) {
    val cameraAnalysis by viewModel.cameraAnalysis.collectAsState()
    val machineSettings by viewModel.machineSettings.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Сканирование детали") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Информация о текущем станке
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Текущий станок: ${machineSettings.machineName}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "AI-анализ: ${if (machineSettings.aiEnabled) "включен" else "выключен"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Заглушка для камеры
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Camera,
                            contentDescription = "Камера",
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Режим сканирования камеры")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Наведите камеру на деталь для анализа",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка анализа (заглушка)
            Button(
                onClick = {
                    // Имитация анализа изображения
                    viewModel.analyzeImage(ByteArray(0))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(Icons.Default.Info, contentDescription = "Анализировать")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Анализировать изображение")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Результаты анализа
            cameraAnalysis?.let { analysis ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Результаты анализа",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("Обнаружен объект: ${analysis.detectedObject}", fontWeight = FontWeight.Bold)
                        Text("Точность: ${(analysis.confidence * 100).toInt()}%")
                        Text("Материал: ${analysis.materialType}")

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("Рекомендуемые инструменты:", fontWeight = FontWeight.Bold)
                        analysis.recommendedTools.forEach { tool ->
                            Text("• $tool", modifier = Modifier.padding(vertical = 2.dp))
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("Рекомендуемые параметры:", fontWeight = FontWeight.Bold)
                        analysis.recommendedParameters.forEach { param ->
                            Text("• $param", modifier = Modifier.padding(vertical = 2.dp))
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("Предупреждения:", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.error)
                        analysis.warnings.forEach { warning ->
                            Text("• $warning",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            } ?: run {
                // Сообщение когда анализа нет
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Анализ не выполнен",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Нажмите кнопку 'Анализировать изображение' для получения рекомендаций",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCameraScanScreen() {
    CNCCalcTheme {
        CameraScanScreen(
            onBackClick = {}
        )
    }
}