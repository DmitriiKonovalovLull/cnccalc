package com.example.cnccalc.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cnccalc.R
import com.example.cnccalc.ui.theme.CNCCalcTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Settings) }
    var drawerState = remember { DrawerState(DrawerValue.Closed) }
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            NavigationDrawerContent(
                currentScreen = currentScreen,
                onItemClick = { screen ->
                    currentScreen = screen
                    scope.launch { drawerState.close() }
                },
                onProfileClick = {
                    currentScreen = Screen.Profile
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBarWithProfile(
                    title = currentScreen.title,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onProfileClick = { currentScreen = Screen.Profile }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (currentScreen) {
                    Screen.Settings -> SettingsScreen(onBackClick = {})
                    Screen.MyTools -> MyToolsScreen()
                    Screen.MyParts -> MyPartsScreen()
                    Screen.Camera -> CameraScanScreen(onBackClick = {})
                    Screen.Profile -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun NavigationDrawerContent(
    currentScreen: Screen,
    onItemClick: (Screen) -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // Заголовок Drawer с профилем
        DrawerHeader(onProfileClick = onProfileClick)

        Spacer(modifier = Modifier.height(8.dp))

        // Пункты меню
        LazyColumn {
            items(Screen.drawerScreens) { screen ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = screen.title,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    selected = currentScreen == screen,
                    onClick = { onItemClick(screen) },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title
                        )
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    }
}

@Composable
fun DrawerHeader(onProfileClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Аватар пользователя
        Image(
            painter = painterResource(id = R.drawable.ic_profile_placeholder),
            contentDescription = "Профиль",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Иван Петров",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Оператор ЧПУ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onProfileClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Мой профиль")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithProfile(
    title: String,
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = "Меню")
            }
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Icon(Icons.Default.Person, contentDescription = "Профиль")
            }
        }
    )
}

sealed class Screen(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Settings : Screen("Настройки", Icons.Default.Settings)
    object MyTools : Screen("Мой инструмент", Icons.Default.Build)
    object MyParts : Screen("Мои детали", Icons.Default.Dashboard)
    object Camera : Screen("Камера", Icons.Default.Camera)
    object Profile : Screen("Профиль", Icons.Default.Person)

    companion object {
        val drawerScreens = listOf(Settings, MyTools, MyParts, Camera)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    CNCCalcTheme {
        MainScreen()
    }
}