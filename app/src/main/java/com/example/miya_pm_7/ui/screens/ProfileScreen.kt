package com.example.miya_pm_7.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.miya_pm_7.R
import com.example.miya_pm_7.ui.components.AppTopBarBack
import kotlin.random.Random

data class AnimatedSymbol(val symbol: String, val startX: Float, val startY: Float, val speed: Float)

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    bottomBar: @Composable () -> Unit,
    fontFamily: FontFamily = FontFamily.Default
) {
    val symbols = listOf(
        "❤️", "⭐", "📚", "💻", "🎵",
        "🎨", "🍀", "🌸", "🍎", "🐱",
        "🐶", "🌞", "🌙", "🎁", "🍰",
        "☕", "🍩", "🌈", "🎯", "📷"
    )
    var activeSymbols by remember { mutableStateOf(listOf<AnimatedSymbol>()) }

    Scaffold(
        topBar = { if (showTopBar) AppTopBarBack(title = "Profile", onBack = onBack) },
        bottomBar = { if (showBottomBar) bottomBar() }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.profile_photo), contentDescription = "Foto Profil",
                    modifier = Modifier.size(120.dp).clip(RoundedCornerShape(60.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Nama Lengkap: Nurmiyaty", style = MaterialTheme.typography.headlineSmall.copy(fontFamily = fontFamily))
                Spacer(modifier = Modifier.height(16.dp))

                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Lokal      : TI 23B", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("NIM        : 230104040083", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Hobi       : Main Game, Dance, Dan Masak", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Asal       : Kalimantan Selatan", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("TTL        : Banjarmasin, 14 Februari 2003", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(symbols) { symbol ->
                            Box(
                                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                                    .clickable {
                                        val newSymbols = List(20) { AnimatedSymbol(symbol, Random.nextFloat(), Random.nextFloat(), Random.nextFloat()*4+2) }
                                        activeSymbols = activeSymbols + newSymbols
                                    },
                                contentAlignment = Alignment.Center
                            ) { Text(symbol, style = MaterialTheme.typography.titleMedium.copy(fontFamily = fontFamily)) }
                        }
                    }
                }
            }

            // Animasi simbol
            activeSymbols.forEach { symbol ->
                val animY = remember { Animatable(symbol.startY*800f) }
                LaunchedEffect(Unit) {
                    animY.animateTo(targetValue = -200f, animationSpec = tween(durationMillis = (symbol.speed*300).toInt(), easing = LinearEasing))
                }
                Text(symbol.symbol, modifier = Modifier.graphicsLayer {
                    translationX = symbol.startX*800f-400f
                    translationY = animY.value
                    alpha = 1f-(animY.value+200)/1000f
                }, style = MaterialTheme.typography.headlineMedium.copy(fontFamily = fontFamily))
            }

            LaunchedEffect(activeSymbols) {
                if(activeSymbols.isNotEmpty()) {
                    kotlinx.coroutines.delay(1200)
                    activeSymbols = emptyList()
                }
            }
        }
    }
}
