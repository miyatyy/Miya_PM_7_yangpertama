package com.example.miya_pm_7.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.miya_pm_7.ui.components.AppTopBar
import com.example.miya_pm_7.ui.components.PrimaryButton
import kotlin.random.Random

@Composable
fun HomeScreen(
    onNavigateProfile: () -> Unit = {},
    onNavigateSettings: () -> Unit = {},
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    bottomBar: @Composable () -> Unit,
    fontFamily: FontFamily = FontFamily.Default
) {
    val symbols = listOf(
        "❤️","⭐","📚","💻","🎵","🎨","🍀","🌸","🍎","🐱",
        "🐶","🌞","🌙","🎁","🍰","☕","🍩","🌈","🎯","📷"
    )

    Scaffold(
        topBar = { if (showTopBar) AppTopBar(title = "Home") },
        bottomBar = { if (showBottomBar) bottomBar() }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Background symbols
            val screenWidth = 360f
            val screenHeight = 640f
            repeat(30) {
                val symbol = symbols.random()
                val x = Random.nextFloat() * screenWidth
                val y = Random.nextFloat() * screenHeight
                val size = Random.nextInt(30, 50).sp
                Text(
                    text = symbol,
                    fontSize = size,
                    color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()),
                    modifier = Modifier.offset(x.dp, y.dp).zIndex(0f)
                )
            }

            // Konten utama
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome MIYA!", style = MaterialTheme.typography.headlineLarge.copy(fontFamily = fontFamily))
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().height(180.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.weight(1f).fillMaxHeight().clickable { onNavigateProfile() },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Profile", style = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily))
                        }
                    }
                    Card(
                        modifier = Modifier.weight(1f).fillMaxHeight().clickable { onNavigateSettings() },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Settings", style = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                PrimaryButton(text = "Go to Profile", onClick = onNavigateProfile, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(12.dp))
                PrimaryButton(text = "Settings", onClick = onNavigateSettings, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
