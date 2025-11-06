package com.example.cnccalc.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainMenu(navController)
        }
        // TODO: Add other destinations
    }
}

@Composable
fun MainMenu(navController: NavController) {
    val menuItems = listOf(
        MenuItem("Drawing Analysis", Icons.Default.Photo, "drawing"),
        MenuItem("CNC Assistant", Icons.Default.Chat, "assistant"),
        MenuItem("Tool Library", Icons.Default.Build, "tools"),
        MenuItem("G-Code Generator", Icons.Default.Code, "gcode")
    )

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
                    onClick = { /* TODO: Navigate to screen */ }
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

data class MenuItem(
    val title: String,
    val icon: androidx.compose.material.icons.Icons.Filled,
    val route: String
)