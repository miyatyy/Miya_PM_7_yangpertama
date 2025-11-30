package com.example.miya_pm7_plus.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.miya_pm7_plus.R
import com.example.miya_pm7_plus.ui.components.AppTopBarBack
import kotlinx.coroutines.delay
import kotlin.random.Random


data class AnimatedSymbol(
    val symbol: String,
    val startX: Float,
    val speed: Float
)

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    bottomBar: @Composable () -> Unit = {}
) {
    var activeSymbols by remember { mutableStateOf(listOf<AnimatedSymbol>()) }
    val allSymbols = listOf("❤️", "⭐", "🌸", "🐱", "🎀", "🍀", "🎁", "📚")

    Scaffold(
        topBar = { if (showTopBar) AppTopBarBack("Profile", onBack) },
        bottomBar = { if (showBottomBar) bottomBar() }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            val screenHeight = 900f
            val screenWidth = 900f

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_photo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(65.dp))
                )

                Spacer(Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text("Nama : Nurmiyaty")
                        Spacer(Modifier.height(8.dp))
                        Text("NIM : 230104040083")
                        Spacer(Modifier.height(8.dp))
                        Text("Hobi : Masak, Dance, Game")
                        Spacer(Modifier.height(8.dp))
                        Text("Asal : Kalimantan Selatan")
                    }
                }

                Spacer(Modifier.height(24.dp))

                val gridItems = List(25) { allSymbols[it % allSymbols.size] }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(gridItems) { symbol ->
                        Text(
                            text = symbol,
                            modifier = Modifier
                                .padding(6.dp)
                                .clickable {
                                    activeSymbols = activeSymbols +
                                            List(12) {
                                                AnimatedSymbol(
                                                    symbol,
                                                    Random.nextFloat(),
                                                    Random.nextFloat() * 3 + 2
                                                )
                                            }
                                },
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }

            activeSymbols.forEach { item ->
                val animY = remember { Animatable(screenHeight) }

                LaunchedEffect(item) {
                    animY.animateTo(
                        targetValue = -200f,
                        animationSpec = tween(
                            durationMillis = (item.speed * 800).toInt(),
                            easing = LinearEasing
                        )
                    )
                }

                Text(
                    text = item.symbol,
                    modifier = Modifier.graphicsLayer {
                        translationX = item.startX * screenWidth
                        translationY = animY.value
                        alpha = (animY.value / screenHeight).coerceIn(0f, 1f)
                    },
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            LaunchedEffect(activeSymbols) {
                if (activeSymbols.isNotEmpty()) {
                    delay(1600)
                    activeSymbols = emptyList()
                }
            }
        }
    }
}
