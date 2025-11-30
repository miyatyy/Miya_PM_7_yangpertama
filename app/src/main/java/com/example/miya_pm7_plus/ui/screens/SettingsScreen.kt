package com.example.miya_pm7_plus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.miya_pm7_plus.ui.components.AppTopBarBack

@Composable
fun SettingsScreen(
    darkMode: Boolean,
    onToggleDark: (Boolean) -> Unit,

    showTopBar: Boolean,
    onToggleTopBar: (Boolean) -> Unit,

    showBottomBar: Boolean,
    onToggleBottomBar: (Boolean) -> Unit,

    onBack: () -> Unit,
    bottomBar: @Composable () -> Unit = {},

    selectedFont: String,
    onFontChange: (String) -> Unit
) {

    val scrollState = rememberScrollState()

    val fontOptions = listOf("Default", "Monospace", "Serif", "Cursive", "SansSerif")

    val fontFamily: FontFamily = when (selectedFont) {
        "Monospace" -> FontFamily.Monospace
        "Serif" -> FontFamily.Serif
        "Cursive" -> FontFamily.Cursive
        "SansSerif" -> FontFamily.SansSerif
        else -> FontFamily.Default
    }

    Scaffold(
        topBar = {
            AppTopBarBack(title = "Settings", onBack = onBack)
        },
        bottomBar = bottomBar
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
                .verticalScroll(scrollState),   // ⭐ BIAR BISA SCROLL
            verticalArrangement = Arrangement.Top
        ) {

            // ======================
            //   THEME MODE SECTION
            // ======================
            Text(
                "Theme Mode",
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily)
            )
            Spacer(Modifier.height(10.dp))

            SettingsCapsule {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Dark Mode",
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(checked = darkMode, onCheckedChange = onToggleDark)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ======================
            //   TOP BAR SWITCH
            // ======================
            SettingsCapsule {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Show Top Bar",
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(checked = showTopBar, onCheckedChange = onToggleTopBar)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ======================
            // BOTTOM BAR SWITCH
            // ======================
            SettingsCapsule {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Show Bottom Bar",
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(checked = showBottomBar, onCheckedChange = onToggleBottomBar)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ======================
            // FONT SECTION
            // ======================
            Text(
                "Select Font",
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily)
            )
            Spacer(Modifier.height(10.dp))

            fontOptions.forEach { font ->

                // ⭐ kapsul dengan padding lebih agar rongga tidak mepet
                SettingsCapsule(
                    modifier = Modifier
                        .padding(vertical = 4.dp)   // ⭐ RONGGA LUAR
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedFont == font,
                            onClick = { onFontChange(font) }
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = font,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = when (font) {
                                    "Monospace" -> FontFamily.Monospace
                                    "Serif" -> FontFamily.Serif
                                    "Cursive" -> FontFamily.Cursive
                                    "SansSerif" -> FontFamily.SansSerif
                                    else -> FontFamily.Default
                                }
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp)) // ⭐ BIAR BAWAH ADA RUANG NAPAS
        }
    }
}

@Composable
private fun SettingsCapsule(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))        // kapsul lebih kecil
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 16.dp, vertical = 12.dp) // ⭐ ruang dalam lebih longgar
    ) {
        content()
    }
}
