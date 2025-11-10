package com.example.cnccalc.domain.models

enum class OperationType {
    MILLING,         // Фрезерование
    DRILLING,        // Сверление
    TAPPING,         // Нарезание резьбы
    TURNING,         // Токарная обработка
    POCKETING,       // Выборка карманов
    CONTOURING,      // Контурная обработка
    FACING,          // Торцевание
    THREAD_MILLING   // Фрезерование резьбы
}