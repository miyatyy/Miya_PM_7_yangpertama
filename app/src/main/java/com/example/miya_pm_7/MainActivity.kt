package com.example.miya_pm_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.example.miya_pm_7.ui.components.CuteBottomBar
import com.example.miya_pm_7.ui.screens.*
import com.example.miya_pm_7.ui.theme.MiyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // --- THEME & BAR STATES ---
            var useDark by remember { mutableStateOf(false) }
            var currentScreen by remember { mutableStateOf("login") }
            var showTopBar by remember { mutableStateOf(true) }
            var showBottomBar by remember { mutableStateOf(true) }

            // --- FONT STATE GLOBAL ---
            var selectedFont by remember { mutableStateOf("Default") }

            MiyaTheme(useDarkTheme = useDark) {
                Surface {

                    // Shared bottom bar
                    val bottomBar: @Composable () -> Unit = {
                        if (showBottomBar) {
                            CuteBottomBar(
                                currentScreen = currentScreen,
                                onHome = { currentScreen = "home" },
                                onProfile = { currentScreen = "profile" },
                                onSettings = { currentScreen = "settings" }
                            )
                        }
                    }

                    when (currentScreen) {

                        "login" -> LoginScreen(
                            onLogin = {
                                currentScreen = "home"
                                showTopBar = true
                                showBottomBar = true
                            }
                        )

                        "home" -> HomeScreen(
                            onNavigateProfile = { currentScreen = "profile" },
                            onNavigateSettings = { currentScreen = "settings" },
                            showTopBar = showTopBar,
                            showBottomBar = showBottomBar,
                            bottomBar = bottomBar
                        )

                        "profile" -> ProfileScreen(
                            onBack = { currentScreen = "home" },
                            showTopBar = showTopBar,
                            showBottomBar = showBottomBar,
                            bottomBar = bottomBar
                        )

                        "settings" -> SettingsScreen(
                            darkMode = useDark,
                            onToggleDark = { useDark = it },
                            showTopBar = showTopBar,
                            onToggleTopBar = { showTopBar = it },
                            showBottomBar = showBottomBar,
                            onToggleBottomBar = { showBottomBar = it },
                            onBack = { currentScreen = "home" },
                            bottomBar = bottomBar,
                            selectedFont = selectedFont,                  // ✅ wajib
                            onFontChange = { selectedFont = it }         // ✅ wajib
                        )
                    }
                }
            }
        }
    }
}
