package com.example.cnccalc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cnccalc.ui.theme.CNCCalcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPartsScreen() {
    var parts by remember { mutableStateOf(emptyList<PartItem>()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Добавить деталь */ }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Добавить деталь")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (parts.isEmpty()) {
                EmptyPartsState()
            } else {
                PartsList(parts = parts)
            }
        }
    }
}

@Composable
fun EmptyPartsState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.Dashboard,
                contentDescription = "Детали",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Нет деталей",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Добавьте вашу первую деталь",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun PartsList(parts: List<PartItem>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(parts) { part ->
            PartCard(part = part)
        }
    }
}

@Composable
fun PartCard(part: PartItem) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = part.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Материал: ${part.material} • Количество: ${part.quantity}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Размеры: ${part.dimensions}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class PartItem(
    val id: String,
    val name: String,
    val material: String,
    val quantity: Int,
    val dimensions: String
)

@Preview(showBackground = true)
@Composable
fun PreviewMyPartsScreen() {
    CNCCalcTheme {
        MyPartsScreen()
    }
}