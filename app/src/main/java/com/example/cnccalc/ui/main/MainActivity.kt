package com.example.cnccalc.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.cnccalc.ui.assistant.AssistantActivity
import com.example.cnccalc.ui.drawing.DrawingProcessActivity
import com.example.cnccalc.ui.theme.CNCCalcTheme
import com.example.cnccalc.ui.tools.ToolsActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CNCCalcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("CNC Calculator") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(menuItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(100.dp),
                    onClick = {
                        when (item.route) {
                            "drawing" -> {
                                context.startActivity(Intent(context, DrawingProcessActivity::class.java))
                            }
                            "assistant" -> {
                                context.startActivity(Intent(context, AssistantActivity::class.java))
                            }
                            "tools" -> {
                                context.startActivity(Intent(context, ToolsActivity::class.java))
                            }
                            "gcode" -> {
                                // Пока заглушка
                            }
                        }
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}

val menuItems = listOf(
    MenuItem("Анализ чертежей", Icons.Default.Photo, "drawing"),
    MenuItem("CNC Ассистент", Icons.Default.Chat, "assistant"),
    MenuItem("Библиотека инструментов", Icons.Default.Build, "tools"),
    MenuItem("Генератор G-кода", Icons.Default.Code, "gcode")
)

data class MenuItem(
    val title: String,
    val icon: androidx.compose.material.icons.Icons.Filled,
    val route: String
)