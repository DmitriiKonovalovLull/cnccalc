package com.example.cnccalc.domain.models.material

enum class MaterialType(
    val category: String,
    val commonGrades: List<String>,
    val russianGrades: List<String>,
    val internationalGrades: List<String>,
    val typicalProperties: MaterialProperties
) {
    ALUMINUM_6061(
        category = "Aluminum Alloy",
        commonGrades = listOf("6061-T6", "6061-T651"),
        russianGrades = listOf("АД33", "АД31"),
        internationalGrades = listOf("6082", "6063"),
        typicalProperties = MaterialProperties(
            hardnessHB = 95f,
            tensileStrength = 310f,
            yieldStrength = 276f,
            elongation = 12f,
            thermalConductivity = 167f,
            machinability = 85f
        )
    ),

    ALUMINUM_7075(
        category = "Aluminum Alloy",
        commonGrades = listOf("7075-T6", "7075-T651"),
        russianGrades = listOf("АЛ19", "В95"),
        internationalGrades = listOf("7075"),
        typicalProperties = MaterialProperties(
            hardnessHB = 150f,
            tensileStrength = 572f,
            yieldStrength = 503f,
            elongation = 11f,
            thermalConductivity = 130f,
            machinability = 70f
        )
    ),

    ALUMINUM_D16(
        category = "Aluminum Alloy",
        commonGrades = listOf("D16", "2024"),
        russianGrades = listOf("Д16", "Д16Т", "Д16АТ", "Д16ЧТ"),
        internationalGrades = listOf("2024", "AA2024"),
        typicalProperties = MaterialProperties(
            hardnessHB = 120f,
            tensileStrength = 470f,
            yieldStrength = 325f,
            elongation = 10f,
            thermalConductivity = 121f,
            machinability = 70f
        )
    ),

    ALUMINUM_AMG(
        category = "Aluminum Alloy",
        commonGrades = listOf("5052", "5056"),
        russianGrades = listOf("АМг", "АМг3", "АМг6"),
        internationalGrades = listOf("AlMg3", "AlMg6"),
        typicalProperties = MaterialProperties(
            hardnessHB = 65f,
            tensileStrength = 230f,
            yieldStrength = 150f,
            elongation = 20f,
            thermalConductivity = 138f,
            machinability = 80f
        )
    ),

    STEEL_MILD(
        category = "Carbon Steel",
        commonGrades = listOf("A36", "1020", "1045"),
        russianGrades = listOf("Ст3", "Сталь3"),
        internationalGrades = listOf("1.0038", "S235JR"),
        typicalProperties = MaterialProperties(
            hardnessHB = 137f,
            tensileStrength = 400f,
            yieldStrength = 250f,
            elongation = 20f,
            thermalConductivity = 51f,
            machinability = 65f
        )
    ),

    STEEL_45(
        category = "Carbon Steel",
        commonGrades = listOf("1045", "C45", "S45C"),
        russianGrades = listOf("45", "Сталь45"),
        internationalGrades = listOf("1.0503"),
        typicalProperties = MaterialProperties(
            hardnessHB = 170f,
            tensileStrength = 600f,
            yieldStrength = 350f,
            elongation = 16f,
            thermalConductivity = 48f,
            machinability = 60f
        )
    ),

    STEEL_40X(
        category = "Alloy Steel",
        commonGrades = listOf("5140", "41Cr4"),
        russianGrades = listOf("40Х", "40ХН", "40ХГ"),
        internationalGrades = listOf("1.7035"),
        typicalProperties = MaterialProperties(
            hardnessHB = 200f,
            tensileStrength = 700f,
            yieldStrength = 500f,
            elongation = 14f,
            thermalConductivity = 42f,
            machinability = 50f
        )
    ),

    STEEL_TOOL(
        category = "Tool Steel",
        commonGrades = listOf("D2", "A2", "O1"),
        russianGrades = listOf("Х12МФ", "У8", "У10"),
        internationalGrades = listOf("1.2379", "1.2363"),
        typicalProperties = MaterialProperties(
            hardnessHB = 220f,
            tensileStrength = 760f,
            yieldStrength = 690f,
            elongation = 14f,
            thermalConductivity = 26f,
            machinability = 35f
        )
    ),

    STEEL_13X18(
        category = "Stainless Steel",
        commonGrades = listOf("420", "420A"),
        russianGrades = listOf("13Х18", "13Х18Н9"),
        internationalGrades = listOf("AISI420", "1.4021"),
        typicalProperties = MaterialProperties(
            hardnessHB = 200f,
            tensileStrength = 650f,
            yieldStrength = 450f,
            elongation = 15f,
            thermalConductivity = 25f,
            machinability = 40f
        )
    ),

    STEEL_20X13(
        category = "Stainless Steel",
        commonGrades = listOf("420", "1.4021"),
        russianGrades = listOf("20Х13", "2Х13"),
        internationalGrades = listOf("AISI420"),
        typicalProperties = MaterialProperties(
            hardnessHB = 220f,
            tensileStrength = 680f,
            yieldStrength = 490f,
            elongation = 12f,
            thermalConductivity = 23f,
            machinability = 35f
        )
    ),

    STAINLESS_304(
        category = "Stainless Steel",
        commonGrades = listOf("304", "304L", "304H"),
        russianGrades = listOf("08Х18Н10", "12Х18Н9"),
        internationalGrades = listOf("AISI304", "1.4301"),
        typicalProperties = MaterialProperties(
            hardnessHB = 170f,
            tensileStrength = 505f,
            yieldStrength = 215f,
            elongation = 40f,
            thermalConductivity = 16f,
            machinability = 45f
        )
    ),

    STAINLESS_316(
        category = "Stainless Steel",
        commonGrades = listOf("316", "316L", "316Ti"),
        russianGrades = listOf("03Х17Н14М2", "10Х17Н13М2Т"),
        internationalGrades = listOf("AISI316", "1.4401"),
        typicalProperties = MaterialProperties(
            hardnessHB = 217f,
            tensileStrength = 515f,
            yieldStrength = 205f,
            elongation = 40f,
            thermalConductivity = 16f,
            machinability = 40f
        )
    ),

    STAINLESS_321(
        category = "Stainless Steel",
        commonGrades = listOf("321", "321H"),
        russianGrades = listOf("08Х18Н10Т", "12Х18Н10Т"),
        internationalGrades = listOf("AISI321", "1.4541"),
        typicalProperties = MaterialProperties(
            hardnessHB = 180f,
            tensileStrength = 520f,
            yieldStrength = 205f,
            elongation = 40f,
            thermalConductivity = 16f,
            machinability = 45f
        )
    ),

    BRASS(
        category = "Copper Alloy",
        commonGrades = listOf("C360", "C260", "C280"),
        russianGrades = listOf("Л63", "ЛС59", "ЛА77"),
        internationalGrades = listOf("CZ121", "CuZn37"),
        typicalProperties = MaterialProperties(
            hardnessHB = 100f,
            tensileStrength = 370f,
            yieldStrength = 125f,
            elongation = 53f,
            thermalConductivity = 115f,
            machinability = 100f
        )
    ),

    BRONZE(
        category = "Copper Alloy",
        commonGrades = listOf("C93200", "SAE660"),
        russianGrades = listOf("БрОЦС", "БрАЖ", "БрКМц"),
        internationalGrades = listOf("C90500", "C51000"),
        typicalProperties = MaterialProperties(
            hardnessHB = 80f,
            tensileStrength = 340f,
            yieldStrength = 140f,
            elongation = 25f,
            thermalConductivity = 75f,
            machinability = 80f
        )
    ),

    TITANIUM(
        category = "Titanium Alloy",
        commonGrades = listOf("Grade 2", "Grade 5", "6Al-4V"),
        russianGrades = listOf("ВТ1", "ВТ6", "ВТ8"),
        internationalGrades = listOf("3.7035", "3.7165"),
        typicalProperties = MaterialProperties(
            hardnessHB = 200f,
            tensileStrength = 900f,
            yieldStrength = 830f,
            elongation = 10f,
            thermalConductivity = 7f,
            machinability = 25f
        )
    );

    fun getRecommendedCuttingSpeed(toolMaterial: String): Float {
        return when (toolMaterial) {
            "Carbide" -> when (this) {
                ALUMINUM_6061, ALUMINUM_7075, ALUMINUM_D16, ALUMINUM_AMG -> 200f
                STEEL_MILD, STEEL_45 -> 120f
                STEEL_40X, STEEL_TOOL -> 80f
                STEEL_13X18, STEEL_20X13 -> 70f
                STAINLESS_304, STAINLESS_316, STAINLESS_321 -> 60f
                BRASS -> 150f
                BRONZE -> 120f
                TITANIUM -> 40f
                else -> 100f
            }
            "HSS" -> when (this) {
                ALUMINUM_6061, ALUMINUM_7075, ALUMINUM_D16, ALUMINUM_AMG -> 100f
                STEEL_MILD, STEEL_45 -> 30f
                STEEL_40X, STEEL_TOOL -> 15f
                STEEL_13X18, STEEL_20X13 -> 12f
                STAINLESS_304, STAINLESS_316, STAINLESS_321 -> 20f
                BRASS -> 60f
                BRONZE -> 50f
                TITANIUM -> 10f
                else -> 25f
            }
            else -> 50f
        }
    }

    companion object {
        fun fromInput(input: String): MaterialType? {
            val normalizedInput = input.uppercase()
                .replace("Х", "X")
                .replace(" ", "")
                .replace("-", "")

            return values().firstOrNull { material ->
                material.commonGrades.any { it.contains(normalizedInput) } ||
                        material.russianGrades.any { it.contains(normalizedInput) } ||
                        material.internationalGrades.any { it.contains(normalizedInput) } ||
                        normalizedInput.contains(material.commonGrades.first()) ||
                        normalizedInput.contains(material.russianGrades.first())
            }
        }
    }
}
