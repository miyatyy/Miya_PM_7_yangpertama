package com.example.miya_pm_7.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.miya_pm_7.ui.components.CuteBottomBar
import com.example.miya_pm_7.ui.screens.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    useDark: Boolean,
    onToggleDark: (Boolean) -> Unit,
    showTopBar: Boolean,
    onToggleTopBar: (Boolean) -> Unit,
    showBottomBar: Boolean,
    onToggleBottomBar: (Boolean) -> Unit,
    startDestination: String = "login"
) {

    // STATE FONT GLOBAL
    var selectedFont by rememberSaveable { mutableStateOf("Default") }
    val fontFamily: FontFamily = when (selectedFont) {
        "Monospace" -> FontFamily.Monospace
        "Serif" -> FontFamily.Serif
        "Cursive" -> FontFamily.Cursive
        "SansSerif" -> FontFamily.SansSerif
        else -> FontFamily.Default
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable("login") {
            LoginScreen(
                onLogin = { navController.navigate("home") },
                fontFamily = fontFamily // pastikan LoginScreen menerima fontFamily
            )
        }

        composable("home") {
            HomeScreen(
                onNavigateProfile = { navController.navigate("profile") },
                onNavigateSettings = { navController.navigate("settings") },
                showTopBar = showTopBar,
                showBottomBar = showBottomBar,
                bottomBar = {
                    if (showBottomBar) {
                        CuteBottomBar(
                            currentScreen = "home",
                            onHome = { navController.navigate("home") },
                            onProfile = { navController.navigate("profile") },
                            onSettings = { navController.navigate("settings") }
                        )
                    }
                },
                fontFamily = fontFamily
            )
        }

        composable("profile") {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                showTopBar = showTopBar,
                showBottomBar = showBottomBar,
                bottomBar = {
                    if (showBottomBar) {
                        CuteBottomBar(
                            currentScreen = "profile",
                            onHome = { navController.navigate("home") },
                            onProfile = { navController.navigate("profile") },
                            onSettings = { navController.navigate("settings") }
                        )
                    }
                },
                fontFamily = fontFamily
            )
        }

        composable("settings") {
            SettingsScreen(
                darkMode = useDark,
                onToggleDark = onToggleDark,
                showTopBar = showTopBar,
                onToggleTopBar = onToggleTopBar,
                showBottomBar = showBottomBar,
                onToggleBottomBar = onToggleBottomBar,
                onBack = { navController.popBackStack() },
                bottomBar = {
                    if (showBottomBar) {
                        CuteBottomBar(
                            currentScreen = "settings",
                            onHome = { navController.navigate("home") },
                            onProfile = { navController.navigate("profile") },
                            onSettings = { navController.navigate("settings") }
                        )
                    }
                },
                selectedFont = selectedFont,
                onFontChange = { selectedFont = it }
            )
        }
    }
}
