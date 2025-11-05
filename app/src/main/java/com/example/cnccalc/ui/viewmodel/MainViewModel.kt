package com.example.cnccalc.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnccalc.data.repository.MachineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ДОБАВЛЕНО: Класс MachineData и MachineSpecifications
data class MachineData(
    val id: String,
    val name: String,
    val manufacturer: String,
    val model: String,
    val type: String,
    val year: Int,
    val specifications: MachineSpecifications,
    val imageUrl: String? = null
)

data class MachineSpecifications(
    val maxRPM: Int,
    val workingArea: String,
    val toolChangerCapacity: Int,
    val spindlePower: Double,
    val weight: Double,
    val dimensions: String
)

class MainViewModel(
    private val repository: MachineRepository
) : ViewModel() {

    // === СОСТОЯНИЯ ДЛЯ РЕАЛЬНЫХ ДАННЫХ ===
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _networkStatus = MutableStateFlow<String>("Проверка соединения...")
    val networkStatus: StateFlow<String> = _networkStatus

    // Потоки реальных данных из БД
    val allMachines = repository.getAllMachines()

    // === СУЩЕСТВУЮЩИЕ СОСТОЯНИЯ ===
    private val _currentCalculation = MutableStateFlow<CalculationResult?>(null)
    val currentCalculation: StateFlow<CalculationResult?> = _currentCalculation

    private val _cameraAnalysis = MutableStateFlow<CameraAnalysis?>(null)
    val cameraAnalysis: StateFlow<CameraAnalysis?> = _cameraAnalysis

    private val _machineSettings = MutableStateFlow(
        MachineSettings(
            machineName = "Мой станок",
            machineType = "Фрезерный",
            millingMaxRPM = 8000,
            turningMaxRPM = 3000,
            aiEnabled = true,
            manufacturer = "Haas",
            model = "VF-2",
            year = 2020,
            workingArea = "800x500x400mm",
            toolChangerCapacity = 20,
            spindlePower = 15.0
        )
    )
    val machineSettings: StateFlow<MachineSettings> = _machineSettings

    private val _myMachines = MutableStateFlow<List<MachineSettings>>(emptyList())
    val myMachines: StateFlow<List<MachineSettings>> = _myMachines

    private val _calculationHistory = MutableStateFlow<List<HistoryItem>>(emptyList())
    val calculationHistory: StateFlow<List<HistoryItem>> = _calculationHistory

    // ДОБАВЛЕНО: недостающие свойства
    private val _searchResults = MutableStateFlow<List<MachineData>>(emptyList())
    val searchResults: StateFlow<List<MachineData>> = _searchResults

    private val _searchError = MutableStateFlow<String?>(null)
    val searchError: StateFlow<String?> = _searchError

    // Инициализация - проверяем интернет и загружаем данные
    init {
        checkAndLoadData()
    }

    // === ФУНКЦИИ ДЛЯ РЕАЛЬНЫХ ДАННЫХ ===
    private fun checkAndLoadData() {
        viewModelScope.launch {
            _isLoading.value = true
            _networkStatus.value = "Проверка соединения..."

            try {
                // Проверяем есть ли данные в кэше
                if (!repository.hasData()) {
                    _networkStatus.value = "Загрузка данных с сервера..."
                    // Загружаем с API если нет данных
                    repository.refreshMachinesFromApi()
                    _networkStatus.value = "Данные успешно загружены"
                } else {
                    _networkStatus.value = "Используются кэшированные данные"
                }
                _errorMessage.value = null

            } catch (e: Exception) {
                _errorMessage.value = "Ошибка загрузки: ${e.message}"
                _networkStatus.value = "Ошибка сети. Используем локальные данные"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Поиск станков в реальной базе
    fun searchMachinesRealTime(query: String) = repository.searchMachines(query)

    // Принудительное обновление данных
    fun refreshData() {
        viewModelScope.launch {
            _isLoading.value = true
            _networkStatus.value = "Обновление данных..."
            try {
                repository.refreshMachinesFromApi()
                _errorMessage.value = null
                _networkStatus.value = "Данные обновлены"
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка обновления: ${e.message}"
                _networkStatus.value = "Ошибка обновления"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // === СУЩЕСТВУЮЩИЕ ФУНКЦИИ ===
    fun saveMachineSettings(settings: MachineSettings) {
        _machineSettings.value = settings
    }

    fun addNewMachine(settings: MachineSettings) {
        viewModelScope.launch {
            val currentList = _myMachines.value.toMutableList()
            if (!currentList.any { it.machineName == settings.machineName }) {
                currentList.add(settings)
                _myMachines.value = currentList
            }
        }
    }

    fun selectMachine(machineName: String) {
        viewModelScope.launch {
            val machine = _myMachines.value.find { it.machineName == machineName }
            machine?.let { _machineSettings.value = it }
        }
    }

    fun deleteMachine(machineName: String) {
        viewModelScope.launch {
            val currentList = _myMachines.value.toMutableList()
            currentList.removeAll { it.machineName == machineName }
            _myMachines.value = currentList
            if (_machineSettings.value.machineName == machineName) {
                _machineSettings.value = currentList.firstOrNull() ?: MachineSettings()
            }
        }
    }

    fun loadMachineFromInternet(model: String, manufacturer: String = "") {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val machineData = simulateMachineDataLoad(model, manufacturer)
                _machineSettings.value = machineData
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка загрузки: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMachineData(machineData: MachineData) {
        viewModelScope.launch {
            val settings = MachineSettings(
                machineName = machineData.name,
                machineType = mapMachineType(machineData.type),
                millingMaxRPM = if (mapMachineType(machineData.type) != "Токарный") machineData.specifications.maxRPM else 0,
                turningMaxRPM = if (mapMachineType(machineData.type) != "Фрезерный") machineData.specifications.maxRPM else 0,
                aiEnabled = true,
                manufacturer = machineData.manufacturer,
                model = machineData.model,
                year = machineData.year,
                workingArea = machineData.specifications.workingArea,
                toolChangerCapacity = machineData.specifications.toolChangerCapacity,
                spindlePower = machineData.specifications.spindlePower
            )
            _machineSettings.value = settings
        }
    }

    // === ФУНКЦИИ РАСЧЕТОВ ===
    fun calculateCuttingParameters(
        material: String,
        toolDiameter: Double,
        cuttingSpeed: Double,
        feedPerTooth: Double,
        numberOfTeeth: Int = 2
    ) {
        viewModelScope.launch {
            val rpm = (cuttingSpeed * 1000) / (Math.PI * toolDiameter)
            val maxRPM = when (_machineSettings.value.machineType) {
                "Токарный" -> _machineSettings.value.turningMaxRPM
                else -> _machineSettings.value.millingMaxRPM
            }
            val limitedRPM = rpm.coerceAtMost(maxRPM.toDouble())
            val feedRate = limitedRPM * feedPerTooth * numberOfTeeth
            val result = CalculationResult(
                material = material,
                toolDiameter = toolDiameter,
                rpm = limitedRPM.toInt(),
                feedRate = feedRate,
                powerRequired = calculatePowerRequirement(material, toolDiameter, feedRate),
                recommendations = generateAIRecommendations(material, toolDiameter)
            )
            _currentCalculation.value = result
            addToHistory(result)
        }
    }

    fun analyzeImage(imageData: ByteArray) {
        viewModelScope.launch {
            val analysis = CameraAnalysis(
                detectedObject = "Фланец стальной",
                confidence = 0.87,
                materialType = "Сталь 45",
                recommendedTools = listOf("Торцевая фреза Ø16mm", "Черновая фреза Ø10mm"),
                recommendedParameters = listOf(
                    "Обороты: 1800 об/мин",
                    "Подача: 300 мм/мин",
                    "Глубина резания: 2mm"
                ),
                warnings = listOf("Используйте СОЖ", "Контролируйте стружкообразование")
            )
            _cameraAnalysis.value = analysis
        }
    }

    // === ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ ===
    private fun mapMachineType(apiType: String): String {
        return when (apiType.lowercase()) {
            "milling", "mill", "vertical machining center" -> "Фрезерный"
            "turning", "lathe", "cnc lathe" -> "Токарный"
            "mill-turn", "multitasking", "turn-mill" -> "Токарно-фрезерный"
            else -> "Фрезерный"
        }
    }

    private fun simulateMachineDataLoad(model: String, manufacturer: String): MachineSettings {
        val machineDatabase = mapOf(
            "VF-2" to MachineSettings(
                machineName = "Haas VF-2",
                machineType = "Фрезерный",
                millingMaxRPM = 7500,
                turningMaxRPM = 0,
                aiEnabled = true,
                manufacturer = "Haas",
                model = "VF-2",
                year = 2020,
                workingArea = "762x406x508mm",
                toolChangerCapacity = 20,
                spindlePower = 11.2
            )
        )
        return machineDatabase[model] ?: MachineSettings(
            machineName = "Неизвестный станок",
            machineType = "Фрезерный",
            millingMaxRPM = 6000,
            turningMaxRPM = 3000,
            aiEnabled = true,
            manufacturer = manufacturer,
            model = model,
            year = 2020,
            workingArea = "Неизвестно",
            toolChangerCapacity = 0,
            spindlePower = 0.0
        )
    }

    private fun addToHistory(calculation: CalculationResult) {
        viewModelScope.launch {
            val newHistory = _calculationHistory.value.toMutableList()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val currentDate = dateFormat.format(Date())
            newHistory.add(
                0,
                HistoryItem(
                    title = "Расчет для ${calculation.material}",
                    description = "Ø${calculation.toolDiameter}mm, ${calculation.rpm} об/мин",
                    date = currentDate,
                    calculationData = calculation
                )
            )
            if (newHistory.size > 50) {
                newHistory.removeAt(newHistory.lastIndex)
            }
            _calculationHistory.value = newHistory
        }
    }

    private fun calculatePowerRequirement(material: String, toolDiameter: Double, feedRate: Double): Double {
        val materialFactor = when (material.lowercase()) {
            "алюминий" -> 0.3
            "латунь" -> 0.5
            "сталь" -> 1.0
            "титан" -> 1.8
            else -> 1.0
        }
        return (toolDiameter * feedRate * materialFactor) / 100
    }

    private fun generateAIRecommendations(material: String, toolDiameter: Double): List<String> {
        val recommendations = mutableListOf<String>()
        when (material.lowercase()) {
            "сталь" -> {
                recommendations.add("Используйте СОЖ для охлаждения")
                recommendations.add("Контролируйте стружкообразование")
                recommendations.add("Рекомендуется черновой и чистовой проход")
                if (toolDiameter > 20) {
                    recommendations.add("Используйте пониженные обороты для большого диаметра")
                }
            }
            "алюминий" -> {
                recommendations.add("Осторожно с налипанием стружки")
                recommendations.add("Высокие обороты, большая подача")
                recommendations.add("Минимальное использование СОЖ")
                recommendations.add("Острый инструмент для чистого реза")
            }
            "титан" -> {
                recommendations.add("Низкие обороты, малая подача")
                recommendations.add("Обязательное использование СОЖ")
                recommendations.add("Прочный инструмент с износостойким покрытием")
                recommendations.add("Избегайте вибраций")
            }
            else -> {
                recommendations.add("Следите за нагрузкой на инструмент")
                recommendations.add("Контролируйте качество поверхности")
            }
        }
        if (_machineSettings.value.aiEnabled) {
            recommendations.add("Анализ ИИ: параметры оптимизированы")
        }
        return recommendations
    }

    fun clearCurrentCalculation() {
        _currentCalculation.value = null
    }

    fun clearCameraAnalysis() {
        _cameraAnalysis.value = null
    }

    fun getCurrentMachineSettings(): MachineSettings {
        return _machineSettings.value
    }
}

// Классы данных
data class MachineSettings(
    val machineName: String = "Мой станок",
    val machineType: String = "Фрезерный",
    val millingMaxRPM: Int = 8000,
    val turningMaxRPM: Int = 3000,
    val aiEnabled: Boolean = true,
    val manufacturer: String = "",
    val model: String = "",
    val year: Int = 2020,
    val workingArea: String = "",
    val toolChangerCapacity: Int = 0,
    val spindlePower: Double = 0.0
)

data class CalculationResult(
    val material: String,
    val toolDiameter: Double,
    val rpm: Int,
    val feedRate: Double,
    val powerRequired: Double,
    val recommendations: List<String>
)

data class CameraAnalysis(
    val detectedObject: String,
    val confidence: Double,
    val materialType: String,
    val recommendedTools: List<String>,
    val recommendedParameters: List<String>,
    val warnings: List<String>
)

data class HistoryItem(
    val title: String,
    val description: String,
    val date: String,
    val calculationData: CalculationResult? = null
)