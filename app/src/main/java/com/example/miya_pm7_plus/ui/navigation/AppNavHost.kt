package com.example.miya_pm7_plus.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.miya_pm7_plus.ui.components.BottomBar
import com.example.miya_pm7_plus.ui.screens.*
import androidx.compose.material3.*

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),

    darkMode: Boolean,
    onToggleDark: (Boolean) -> Unit,

    selectedFont: String,
    onFontChange: (String) -> Unit,

    showTopBar: Boolean,
    onToggleTopBar: (Boolean) -> Unit,

    showBottomBar: Boolean,
    onToggleBottomBar: (Boolean) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // =======================
        // LOGIN
        // =======================
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // =======================
        // HOME
        // =======================
        composable("home") {
            HomeScreen(
                onNavigateProfile = { navController.navigate("profile") },
                // Tetap pakai "settings" (route utama)
                onNavigateSettings = { navController.navigate("settings") },

                showTopBar = showTopBar,
                showBottomBar = showBottomBar,
                bottomBar = { if (showBottomBar) BottomBar(navController) }
            )
        }

        // =======================
        // PROFILE
        // =======================
        composable("profile") {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                showTopBar = showTopBar,
                showBottomBar = showBottomBar,
                bottomBar = { if (showBottomBar) BottomBar(navController) }
            )
        }

        // =======================
        // SETTINGS (ROUTE UTAMA)
        // =======================
        composable("settings") {
            SettingsScreen(
                darkMode = darkMode,
                onToggleDark = onToggleDark,

                showTopBar = showTopBar,
                onToggleTopBar = onToggleTopBar,

                showBottomBar = showBottomBar,
                onToggleBottomBar = onToggleBottomBar,

                selectedFont = selectedFont,
                onFontChange = onFontChange,

                onBack = { navController.popBackStack() },

                bottomBar = { if (showBottomBar) BottomBar(navController) }
            )
        }

        // =======================
        // ALIAS ROUTE "setting"
        // Agar BottomBar.navigate("setting") tidak crash
        // =======================
        composable("setting") {
            navController.navigate("settings") {
                popUpTo("setting") { inclusive = true }
            }
        }
    }
}
