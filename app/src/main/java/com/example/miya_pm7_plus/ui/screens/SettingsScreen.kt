package com.example.miya_pm7_plus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    bottomBar: @Composable () -> Unit,
    selectedFont: String,
    onFontChange: (String) -> Unit
) {
    val fontOptions = listOf("Default","Monospace","Serif","Cursive","SansSerif")
    val fontFamily: FontFamily = when(selectedFont) {
        "Monospace" -> FontFamily.Monospace
        "Serif" -> FontFamily.Serif
        "Cursive" -> FontFamily.Cursive
        "SansSerif" -> FontFamily.SansSerif
        else -> FontFamily.Default
    }

    Scaffold(topBar = { AppTopBarBack(title="Settings", onBack=onBack) }, bottomBar = bottomBar) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), verticalArrangement = Arrangement.Top) {
            Text("Theme Mode", style = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily))
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Dark Mode", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = darkMode, onCheckedChange = { onToggleDark(it) })
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Show Top Bar", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = showTopBar, onCheckedChange = { onToggleTopBar(it) })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Show Bottom Bar", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = showBottomBar, onCheckedChange = { onToggleBottomBar(it) })
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("Select Font", style = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily))
            Spacer(modifier = Modifier.height(8.dp))
            fontOptions.forEach { font ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(vertical=4.dp)) {
                    RadioButton(selected = selectedFont==font, onClick = { onFontChange(font) })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(font, style = MaterialTheme.typography.bodyLarge.copy(fontFamily = when(font){
                        "Monospace" -> FontFamily.Monospace
                        "Serif" -> FontFamily.Serif
                        "Cursive" -> FontFamily.Cursive
                        "SansSerif" -> FontFamily.SansSerif
                        else -> FontFamily.Default
                    }))
                }
            }
        }
    }
}
