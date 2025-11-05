package com.example.cnccalc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
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
fun MyToolsScreen() {
    var tools by remember { mutableStateOf(emptyList<ToolItem>()) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Добавить инструмент */ }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Добавить инструмент")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (tools.isEmpty()) {
                EmptyToolsState()
            } else {
                ToolsList(tools = tools)
            }
        }
    }
}

@Composable
fun EmptyToolsState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.Build,
                contentDescription = "Инструменты",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Нет инструментов",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Добавьте ваш первый инструмент",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ToolsList(tools: List<ToolItem>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tools) { tool ->
            ToolCard(tool = tool)
        }
    }
}

@Composable
fun ToolCard(tool: ToolItem) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = tool.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Тип: ${tool.type} • Диаметр: ${tool.diameter}mm",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Материал: ${tool.material}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class ToolItem(
    val id: String,
    val name: String,
    val type: String,
    val diameter: Double,
    val material: String
)

@Preview(showBackground = true)
@Composable
fun PreviewMyToolsScreen() {
    CNCCalcTheme {
        MyToolsScreen()
    }
}