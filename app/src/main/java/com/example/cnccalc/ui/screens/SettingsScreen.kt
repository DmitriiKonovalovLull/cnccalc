package com.example.cnccalc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cnccalc.data.database.MachineEntity
import com.example.cnccalc.ui.theme.CNCCalcTheme
import com.example.cnccalc.ui.viewmodel.MainViewModel
import com.example.cnccalc.ui.viewmodel.mainViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    viewModel: MainViewModel = mainViewModel()
) {
    // === СОСТОЯНИЯ РЕАЛЬНЫХ ДАННЫХ ===
    val allMachines by viewModel.allMachines.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()
    val networkStatus by viewModel.networkStatus.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // === СУЩЕСТВУЮЩИЕ СОСТОЯНИЯ ===
    val currentSettings by viewModel.machineSettings.collectAsState()
    val myMachines by viewModel.myMachines.collectAsState()

    // Временные состояния для поиска (пока не реализовано в ViewModel)
    val searchResults = remember { mutableStateListOf<com.example.cnccalc.data.api.MachineData>() }
    val searchError = remember { mutableStateOf<String?>(null) }

    var machineName by remember { mutableStateOf(currentSettings.machineName) }
    var machineType by remember { mutableStateOf(currentSettings.machineType) }
    var millingMaxRPM by remember { mutableStateOf(currentSettings.millingMaxRPM.toString()) }
    var turningMaxRPM by remember { mutableStateOf(currentSettings.turningMaxRPM.toString()) }
    var aiEnabled by remember { mutableStateOf(currentSettings.aiEnabled) }
    var selectedMachine by remember { mutableStateOf(currentSettings.machineName) }
    var manufacturer by remember { mutableStateOf(currentSettings.manufacturer) }
    var model by remember { mutableStateOf(currentSettings.model) }
    var year by remember { mutableStateOf(currentSettings.year.toString()) }
    var workingArea by remember { mutableStateOf(currentSettings.workingArea) }
    var toolChangerCapacity by remember { mutableStateOf(currentSettings.toolChangerCapacity.toString()) }
    var spindlePower by remember { mutableStateOf(currentSettings.spindlePower.toString()) }

    var showSuggestions by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var showRealMachines by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val machineTypes = listOf("Фрезерный", "Токарный", "Токарно-фрезерный")

    // Обновляем форму при изменении текущих настроек
    LaunchedEffect(currentSettings) {
        machineName = currentSettings.machineName
        machineType = currentSettings.machineType
        millingMaxRPM = currentSettings.millingMaxRPM.toString()
        turningMaxRPM = currentSettings.turningMaxRPM.toString()
        aiEnabled = currentSettings.aiEnabled
        manufacturer = currentSettings.manufacturer
        model = currentSettings.model
        year = currentSettings.year.toString()
        workingArea = currentSettings.workingArea
        toolChangerCapacity = currentSettings.toolChangerCapacity.toString()
        spindlePower = currentSettings.spindlePower.toString()
        selectedMachine = currentSettings.machineName
    }

    LaunchedEffect(searchQuery) {
        if (searchQuery.length >= 2) {
            delay(300)
            if (searchQuery.length >= 2) {
                // Временная заглушка для поиска
                searchError.value = null
                // viewModel.searchMachinesRealTime(searchQuery) // Раскомментировать когда будет готов API
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Настройки станка") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.refreshData() },
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        } else {
                            Icon(Icons.Default.Refresh, contentDescription = "Обновить")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            // Статус сети
            if (errorMessage != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = errorMessage!!,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            Text(
                text = networkStatus,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Переключатель между моими станками и базой данных
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (showRealMachines) "База станков (${allMachines.size})" else "Мои станки",
                    style = MaterialTheme.typography.headlineSmall
                )
                Switch(
                    checked = showRealMachines,
                    onCheckedChange = { showRealMachines = it }
                )
            }

            if (showRealMachines) {
                // === РЕАЛЬНЫЕ ДАННЫЕ ИЗ БАЗЫ ===
                if (allMachines.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator()
                        } else {
                            Text(
                                text = "Нет данных о станках",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 400.dp)
                    ) {
                        items(allMachines) { machine ->
                            RealMachineCard(
                                machine = machine,
                                onSelect = {
                                    // При выборе станка из базы заполняем форму
                                    val settings = com.example.cnccalc.ui.viewmodel.MachineSettings(
                                        machineName = machine.name,
                                        machineType = when (machine.type) {
                                            "milling", "mill" -> "Фрезерный"
                                            "turning", "lathe" -> "Токарный"
                                            "mill-turn" -> "Токарно-фрезерный"
                                            else -> "Фрезерный"
                                        },
                                        millingMaxRPM = if (machine.type != "turning" && machine.type != "lathe") machine.maxRPM else 0,
                                        turningMaxRPM = if (machine.type == "turning" || machine.type == "lathe") machine.maxRPM else 0,
                                        aiEnabled = true,
                                        manufacturer = machine.manufacturer,
                                        model = machine.model,
                                        year = machine.year,
                                        workingArea = machine.workingArea,
                                        toolChangerCapacity = machine.toolChangerCapacity,
                                        spindlePower = machine.spindlePower
                                    )
                                    viewModel.saveMachineSettings(settings)
                                    showRealMachines = false // Переключаемся обратно на форму
                                }
                            )
                        }
                    }
                }
            } else {
                // === СУЩЕСТВУЮЩИЙ ИНТЕРФЕЙС ===
                if (myMachines.isNotEmpty()) {
                    myMachines.forEach { machine ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedMachine == machine.machineName,
                                onClick = {
                                    selectedMachine = machine.machineName
                                    viewModel.selectMachine(machine.machineName)
                                }
                            )
                            Text(
                                text = machine.machineName,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                } else {
                    Text(
                        "У вас пока нет сохраненных станков",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Поиск станка по модели",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Box {
                    Column {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { query ->
                                searchQuery = query
                                showSuggestions = query.length >= 2
                            },
                            label = { Text("Введите модель станка") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            trailingIcon = {
                                if (isLoading) {
                                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                                } else if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = {
                                        searchQuery = ""
                                        showSuggestions = false
                                    }) {
                                        Icon(Icons.Default.Search, contentDescription = "Очистить")
                                    }
                                }
                            },
                            isError = searchError.value != null,
                            supportingText = {
                                if (searchError.value != null) {
                                    Text(searchError.value!!)
                                } else if (searchQuery.isNotEmpty() && searchQuery.length < 2) {
                                    Text("Введите минимум 2 символа")
                                }
                            }
                        )

                        if (showSuggestions && searchQuery.length >= 2 && !isLoading) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                LazyColumn(
                                    modifier = Modifier.heightIn(max = 200.dp)
                                ) {
                                    items(getMachineSuggestions().filter {
                                        it.contains(searchQuery, ignoreCase = true)
                                    }.take(5)) { suggestion ->
                                        TextButton(
                                            onClick = {
                                                searchQuery = suggestion
                                                showSuggestions = false
                                                keyboardController?.hide()
                                                viewModel.loadMachineFromInternet(suggestion, "")
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 8.dp, vertical = 4.dp),
                                        ) {
                                            Text(
                                                text = suggestion,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Основные настройки станка
                Text(
                    "Основные параметры",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
                )

                OutlinedTextField(
                    value = machineName,
                    onValueChange = { machineName = it },
                    label = { Text("Название станка") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = manufacturer,
                    onValueChange = { manufacturer = it },
                    label = { Text("Производитель") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it },
                    label = { Text("Модель") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = year,
                    onValueChange = { year = it },
                    label = { Text("Год выпуска") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Тип станка:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                machineTypes.forEach { type ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = machineType == type,
                            onClick = { machineType = type }
                        )
                        Text(
                            text = type,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Технические характеристики",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (machineType != "Токарный") {
                    OutlinedTextField(
                        value = millingMaxRPM,
                        onValueChange = { millingMaxRPM = it },
                        label = { Text("Макс. обороты фрезерного шпинделя") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (machineType != "Фрезерный") {
                    OutlinedTextField(
                        value = turningMaxRPM,
                        onValueChange = { turningMaxRPM = it },
                        label = { Text("Макс. обороты токарного шпинделя") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                OutlinedTextField(
                    value = workingArea,
                    onValueChange = { workingArea = it },
                    label = { Text("Рабочая зона") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = toolChangerCapacity,
                    onValueChange = { toolChangerCapacity = it },
                    label = { Text("Ёмкость магазина инструментов") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = spindlePower,
                    onValueChange = { spindlePower = it },
                    label = { Text("Мощность шпинделя (кВт)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Использовать ИИ-рекомендации")
                    Switch(
                        checked = aiEnabled,
                        onCheckedChange = { aiEnabled = it }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            val newMachine = com.example.cnccalc.ui.viewmodel.MachineSettings(
                                machineName = machineName,
                                machineType = machineType,
                                millingMaxRPM = millingMaxRPM.toIntOrNull() ?: 8000,
                                turningMaxRPM = turningMaxRPM.toIntOrNull() ?: 3000,
                                aiEnabled = aiEnabled,
                                manufacturer = manufacturer,
                                model = model,
                                year = year.toIntOrNull() ?: 2020,
                                workingArea = workingArea,
                                toolChangerCapacity = toolChangerCapacity.toIntOrNull() ?: 0,
                                spindlePower = spindlePower.toDoubleOrNull() ?: 0.0
                            )
                            viewModel.addNewMachine(newMachine)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Добавить")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Новый станок")
                    }

                    Button(
                        onClick = {
                            val settings = com.example.cnccalc.ui.viewmodel.MachineSettings(
                                machineName = machineName,
                                machineType = machineType,
                                millingMaxRPM = millingMaxRPM.toIntOrNull() ?: 8000,
                                turningMaxRPM = turningMaxRPM.toIntOrNull() ?: 3000,
                                aiEnabled = aiEnabled,
                                manufacturer = manufacturer,
                                model = model,
                                year = year.toIntOrNull() ?: 2020,
                                workingArea = workingArea,
                                toolChangerCapacity = toolChangerCapacity.toIntOrNull() ?: 0,
                                spindlePower = spindlePower.toDoubleOrNull() ?: 0.0
                            )
                            viewModel.saveMachineSettings(settings)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Сохранить")
                    }
                }
            }
        }
    }
}

@Composable
fun RealMachineCard(
    machine: MachineEntity,
    onSelect: () -> Unit
) {
    Card(
        onClick = onSelect,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${machine.manufacturer} ${machine.model}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = machine.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Год: ${machine.year} • ${machine.workingArea}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = "Обороты: ${machine.maxRPM} об/мин • Мощность: ${machine.spindlePower} кВт",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

private fun getMachineSuggestions(): List<String> {
    return listOf(
        "Haas VF-2",
        "Haas VF-4",
        "Haas VF-6",
        "DMG Mori DMU-50",
        "DMG Mori DMU-80",
        "Doosan NLX-2500",
        "Doosan Puma-2600",
        "Mazak VCN-530",
        "Mazak Integrex-200",
        "Okuma MB-46V",
        "Okuma LB-3000"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    CNCCalcTheme {
        SettingsScreen(
            onBackClick = {}
        )
    }
}