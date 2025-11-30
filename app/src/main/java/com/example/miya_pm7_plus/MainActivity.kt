package com.example.miya_pm7_plus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.miya_pm7_plus.ui.navigation.AppNavHost
import com.example.miya_pm7_plus.ui.theme.MiyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // GLOBAL STATE YANG BENAR → di sini
            var darkMode by remember { mutableStateOf(false) }
            var selectedFont by remember { mutableStateOf("Default") }
            var showTopBar by remember { mutableStateOf(true) }
            var showBottomBar by remember { mutableStateOf(true) }

            MiyaTheme(darkTheme = darkMode, fontChoice = selectedFont) {
                AppNavHost(
                    darkMode = darkMode,
                    onToggleDark = { darkMode = it },

                    selectedFont = selectedFont,
                    onFontChange = { selectedFont = it },

                    showTopBar = showTopBar,
                    onToggleTopBar = { showTopBar = it },

                    showBottomBar = showBottomBar,
                    onToggleBottomBar = { showBottomBar = it }
                )
            }
        }
    }
}
