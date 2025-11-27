package com.example.miya_pm7_plus.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    // Display
    displayLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 52.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),

    // Headline
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),

    // Title
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),

    // Body
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    // Label
    labelLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    )
)
