package com.example.miya_pm_7.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.miya_pm_7.ui.components.AppTextField
import com.example.miya_pm_7.ui.components.PrimaryButton
import kotlin.random.Random

@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    fontFamily: FontFamily = FontFamily.Default
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val symbols = listOf(
        "❤️","⭐","📚","💻","🎵","🎨","🍀","🌸","🍎","🐱",
        "🐶","🌞","🌙","🎁","🍰","☕","🍩","🌈","🎯","📷"
    )

    Scaffold(topBar = {}, bottomBar = {}) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            val screenWidth = 360f
            val screenHeight = 640f
            repeat(30) {
                val symbol = symbols.random()
                val x = Random.nextFloat() * screenWidth
                val y = Random.nextFloat() * screenHeight
                val size = Random.nextInt(25, 45).sp
                Text(
                    text = symbol,
                    fontSize = size,
                    color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()),
                    modifier = Modifier.offset(x.dp, y.dp).zIndex(0f)
                )
            }

            Column(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 340.dp)
                    .wrapContentHeight()
                    .background(Color(0xFFFFC0CB), RoundedCornerShape(16.dp))
                    .padding(24.dp)
                    .align(Alignment.Center)
                    .zIndex(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome", style = MaterialTheme.typography.displayMedium.copy(fontFamily = fontFamily))
                Spacer(modifier = Modifier.height(6.dp))
                Text("Sign in to continue", style = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily))
                Spacer(modifier = Modifier.height(24.dp))
                AppTextField(value = email, onValueChange = { email = it }, label = "Email", modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(12.dp))
                AppTextField(value = password, onValueChange = { password = it }, label = "Password", modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(24.dp))
                PrimaryButton(text = "Sign In", onClick = onLogin, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
