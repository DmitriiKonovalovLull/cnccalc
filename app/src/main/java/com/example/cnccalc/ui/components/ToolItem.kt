package com.example.cnccalc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cnccalc.data.db.ToolEntity

@Composable
fun ToolItem(tool: ToolEntity, onDelete: ((ToolEntity) -> Unit)? = null) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = tool.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = tool.type, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Ø ${tool.diameter ?: "-"} mm • L ${tool.length ?: "-"} mm", style = MaterialTheme.typography.bodySmall)
            if (onDelete != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { onDelete(tool) }) {
                    Text("Удалить")
                }
            }
        }
    }
}
