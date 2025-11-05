package com.example.cnccalc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.History
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
fun CalculatorScreen(
    onBackClick: () -> Unit,
    onHistoryClick: () -> Unit,
    viewModel: com.example.cnccalc.ui.viewmodel.MainViewModel = mainViewModel()
) {
    val currentCalculation by viewModel.currentCalculation.collectAsState()
    val machineSettings by viewModel.machineSettings.collectAsState()

    var material by remember { mutableStateOf("Сталь") }
    var toolDiameter by remember { mutableStateOf("10.0") }
    var cuttingSpeed by remember { mutableStateOf("100.0") }
    var feedPerTooth by remember { mutableStateOf("0.1") }
    var numberOfTeeth by remember { mutableStateOf("2") }

    val materials = listOf("Алюминий", "Сталь", "Титан", "Латунь", "Нержавейка")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Калькулятор режимов резания") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    IconButton(onClick = onHistoryClick) {
                        Icon(Icons.Default.History, contentDescription = "История")
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
                        text = "Макс. обороты: ${if (machineSettings.machineType != "Токарный") machineSettings.millingMaxRPM else machineSettings.turningMaxRPM} об/мин",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Параметры материала и инструмента
            Text(
                "Параметры обработки",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = material,
                onValueChange = { material = it },
                label = { Text("Материал") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = toolDiameter,
                onValueChange = { toolDiameter = it },
                label = { Text("Диаметр инструмента (мм)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = cuttingSpeed,
                onValueChange = { cuttingSpeed = it },
                label = { Text("Скорость резания (м/мин)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = feedPerTooth,
                onValueChange = { feedPerTooth = it },
                label = { Text("Подача на зуб (мм)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = numberOfTeeth,
                onValueChange = { numberOfTeeth = it },
                label = { Text("Количество зубьев") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Кнопка расчета
            Button(
                onClick = {
                    viewModel.calculateCuttingParameters(
                        material = material,
                        toolDiameter = toolDiameter.toDoubleOrNull() ?: 10.0,
                        cuttingSpeed = cuttingSpeed.toDoubleOrNull() ?: 100.0,
                        feedPerTooth = feedPerTooth.toDoubleOrNull() ?: 0.1,
                        numberOfTeeth = numberOfTeeth.toIntOrNull() ?: 2
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = toolDiameter.isNotEmpty() && cuttingSpeed.isNotEmpty() && feedPerTooth.isNotEmpty()
            ) {
                Icon(Icons.Default.Calculate, contentDescription = "Рассчитать")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Рассчитать параметры")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Результаты расчета
            currentCalculation?.let { calculation ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Результаты расчета",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ResultRow("Обороты шпинделя:", "${calculation.rpm} об/мин")
                        ResultRow("Подача:", "${String.format("%.1f", calculation.feedRate)} мм/мин")
                        ResultRow("Мощность:", "${String.format("%.1f", calculation.powerRequired)} кВт")

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            "Рекомендации:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        calculation.recommendations.forEach { recommendation ->
                            Text(
                                "• $recommendation",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorScreen() {
    CNCCalcTheme {
        CalculatorScreen(
            onBackClick = {},
            onHistoryClick = {}
        )
    }
}