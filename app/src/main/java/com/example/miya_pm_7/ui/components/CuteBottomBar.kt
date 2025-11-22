package com.example.miya_pm_7.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CuteBottomBar(
    currentScreen: String,
    onHome: () -> Unit,
    onProfile: () -> Unit,
    onSettings: () -> Unit
) {
    // Warna pastel gemes
    val pastelPink = Color(0xFFFFC1E3)
    val pastelPurple = Color(0xFFDFCCFB)
    val pastelBlue = Color(0xFFCDE8FF)

    Surface(
        shadowElevation = 12.dp,
        tonalElevation = 3.dp,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(pastelPink)
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomItem(
                selected = currentScreen == "home",
                icon = Icons.Default.Home,
                label = "Home",
                color = pastelBlue,
                onClick = onHome
            )

            BottomItem(
                selected = currentScreen == "profile",
                icon = Icons.Default.Person,
                label = "Profile",
                color = pastelPurple,
                onClick = onProfile
            )

            BottomItem(
                selected = currentScreen == "settings",
                icon = Icons.Default.Settings,
                label = "Settings",
                color = pastelBlue,
                onClick = onSettings
            )
        }
    }
}

@Composable
fun BottomItem(
    selected: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.25f else 1f,
        animationSpec = androidx.compose.animation.core.spring()
    )

    Column(
        modifier = Modifier
            .scale(scale)
            .padding(4.dp)
            .width(70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier
                    .background(color, RoundedCornerShape(16.dp))
                    .padding(10.dp)
            )
        }

        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )
    }
}
