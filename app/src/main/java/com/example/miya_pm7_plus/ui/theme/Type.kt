package com.example.miya_pm7_plus.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily

// Default Typography kamu
val AppTypography = Typography(
    headlineMedium = Typography().headlineMedium.copy(fontFamily = FontFamily.Default),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = FontFamily.Default),
    titleMedium = Typography().titleMedium.copy(fontFamily = FontFamily.Default),
    bodyLarge = Typography().bodyLarge.copy(fontFamily = FontFamily.Default)
)

// Untuk mengganti seluruh font di theme
fun Typography.applyFontFamily(font: FontFamily): Typography {
    return Typography(
        displayLarge = displayLarge.copy(fontFamily = font),
        displayMedium = displayMedium.copy(fontFamily = font),
        displaySmall = displaySmall.copy(fontFamily = font),

        headlineLarge = headlineLarge.copy(fontFamily = font),
        headlineMedium = headlineMedium.copy(fontFamily = font),
        headlineSmall = headlineSmall.copy(fontFamily = font),

        titleLarge = titleLarge.copy(fontFamily = font),
        titleMedium = titleMedium.copy(fontFamily = font),
        titleSmall = titleSmall.copy(fontFamily = font),

        bodyLarge = bodyLarge.copy(fontFamily = font),
        bodyMedium = bodyMedium.copy(fontFamily = font),
        bodySmall = bodySmall.copy(fontFamily = font),

        labelLarge = labelLarge.copy(fontFamily = font),
        labelMedium = labelMedium.copy(fontFamily = font),
        labelSmall = labelSmall.copy(fontFamily = font)
    )
}
