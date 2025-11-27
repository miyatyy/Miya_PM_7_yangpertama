package com.example.miya_pm7_plus.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.miya_pm7_plus.R
import com.example.miya_pm7_plus.ui.components.AppTopBarBack
import kotlinx.coroutines.delay
import kotlin.random.Random

data class AnimatedSymbol(
    val symbol: String,
    val startX: Float,
    val startY: Float,
    val speed: Float
)

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    bottomBar: @Composable () -> Unit,
    fontFamily: FontFamily = FontFamily.Default
) {
    var screenSize by remember { mutableStateOf(IntSize.Zero) }
    var activeSymbols by remember { mutableStateOf(listOf<AnimatedSymbol>()) }

    val symbols = listOf("❤️", "⭐", "📚", "💻", "🎵", "🎨", "🍀", "🌸", "🍎", "🐱")

    Scaffold(
        topBar = { if (showTopBar) AppTopBarBack("Profile", onBack) },
        bottomBar = { if (showBottomBar) bottomBar() }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .onGloballyPositioned { screenSize = it.size }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.profile_photo),
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(100))
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    "Nurmiyaty",
                    style = MaterialTheme.typography.headlineMedium.copy(fontFamily = fontFamily)
                )

                Text(
                    "Mahasiswi TI 23B",
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = fontFamily)
                )

                Spacer(Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        ProfileRow("Nama", "Nurmiyaty", fontFamily)
                        ProfileRow("NIM", "230104040083", fontFamily)
                        ProfileRow("Lokal", "TI 23B", fontFamily)
                        ProfileRow("Hobi", "Game, Dance, Masak", fontFamily)
                        ProfileRow("Asal", "Kalimantan Selatan", fontFamily)
                        ProfileRow("TTL", "Banjarmasin, 14 Feb 2003", fontFamily)
                    }
                }

                Spacer(Modifier.height(20.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier
                        .height(200.dp)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(symbols) { s ->
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                                .clickable {
                                    val burst = List(15) {
                                        AnimatedSymbol(
                                            s,
                                            Random.nextFloat(),
                                            1f,
                                            Random.nextFloat() * 3f + 2f
                                        )
                                    }
                                    activeSymbols = activeSymbols + burst
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                s,
                                style = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily)
                            )
                        }
                    }
                }
            }

            activeSymbols.forEach { item ->
                if (screenSize.width > 0 && screenSize.height > 0) {
                    val animY = remember { Animatable(item.startY * screenSize.height) }

                    LaunchedEffect(item) {
                        animY.animateTo(
                            -200f,
                            tween(
                                durationMillis = (item.speed * 350).toInt(),
                                easing = LinearEasing
                            )
                        )
                    }

                    Text(
                        text = item.symbol,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.graphicsLayer {
                            translationX = item.startX * screenSize.width
                            translationY = animY.value
                            alpha = (1f - animY.value / screenSize.height).coerceIn(0f, 1f)
                        }
                    )
                }
            }

            LaunchedEffect(activeSymbols) {
                if (activeSymbols.isNotEmpty()) {
                    delay(1500)
                    activeSymbols = emptyList()
                }
            }
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String, fontFamily: FontFamily) {
    Column(Modifier.padding(vertical = 6.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge.copy(fontFamily = fontFamily))
        Text(value, style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
    }
}
