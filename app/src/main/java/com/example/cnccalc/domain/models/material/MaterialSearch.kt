package com.example.cnccalc.domain.models.material

data class MaterialSearchResult(
    val exactMatch: MaterialType?,
    val suggestions: List<MaterialSuggestion>,
    val needOnlineSearch: Boolean
)

data class MaterialSuggestion(
    val materialType: MaterialType,
    val matchedGrade: String,
    val confidence: Float
)

class MaterialSearchEngine {

    fun search(query: String, availableMaterials: List<MaterialType> = MaterialType.entries.toList()): MaterialSearchResult {
        val normalizedQuery = normalizeQuery(query)

        if (normalizedQuery.length < 2) {
            return MaterialSearchResult(null, emptyList(), false)
        }

        val exactMatch = findExactMatch(normalizedQuery, availableMaterials)
        if (exactMatch != null) {
            return MaterialSearchResult(exactMatch, emptyList(), false)
        }

        val suggestions = findSuggestions(normalizedQuery, availableMaterials)
        val needOnlineSearch = suggestions.isEmpty() && normalizedQuery.length >= 3

        return MaterialSearchResult(null, suggestions, needOnlineSearch)
    }

    private fun findExactMatch(query: String, materials: List<MaterialType>): MaterialType? {
        return materials.firstOrNull { material ->
            material.commonGrades.any { grade -> matchesGrade(grade, query) } ||
                    material.russianGrades.any { grade -> matchesGrade(grade, query) } ||
                    material.internationalGrades.any { grade -> matchesGrade(grade, query) } ||
                    matchesHeatTreatment(material, query)
        }
    }

    private fun findSuggestions(query: String, materials: List<MaterialType>): List<MaterialSuggestion> {
        return materials.flatMap { material ->
            val matches = mutableListOf<MaterialSuggestion>()

            material.russianGrades.forEach { grade ->
                val confidence = calculateMatchConfidence(grade, query)
                if (confidence > 0.3f) {
                    matches.add(MaterialSuggestion(material, grade, confidence))
                }
            }

            material.commonGrades.forEach { grade ->
                val confidence = calculateMatchConfidence(grade, query)
                if (confidence > 0.3f) {
                    matches.add(MaterialSuggestion(material, grade, confidence))
                }
            }

            matches
        }.sortedByDescending { it.confidence }
    }

    private fun matchesGrade(grade: String, query: String): Boolean {
        val normalizedGrade = normalizeQuery(grade)
        return normalizedGrade == query ||
                normalizedGrade.contains(query) ||
                query.contains(normalizedGrade)
    }

    private fun matchesHeatTreatment(material: MaterialType, query: String): Boolean {
        val heatTreatmentTerms = mapOf(
            "О" to "annealed",
            "Т" to "hardened",
            "Ч" to "aged",
            "З" to "quenched",
            "Н" to "normalized"
        )

        return heatTreatmentTerms.any { (rus, eng) ->
            query.contains(rus) || query.contains(eng, ignoreCase = true)
        }
    }

    private fun calculateMatchConfidence(grade: String, query: String): Float {
        val normalizedGrade = normalizeQuery(grade)

        return when {
            normalizedGrade == query -> 1.0f
            normalizedGrade.startsWith(query) -> 0.8f
            query.startsWith(normalizedGrade) -> 0.7f
            normalizedGrade.contains(query) -> 0.6f
            else -> {
                val maxLength = maxOf(normalizedGrade.length, query.length)
                val commonChars = normalizedGrade.toSet().intersect(query.toSet()).size
                commonChars.toFloat() / maxLength
            }
        }
    }

    private fun normalizeQuery(query: String): String {
        return query.uppercase()
            .replace("Х", "X")
            .replace(" ", "")
            .replace("-", "")
            .replace("_", "")
            .replace(",", "")
            .replace(".", "")
    }
}