package com.example.miya_pm7_plus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

// =============================
//  COLOR SCHEME LIGHT
// =============================
private val LightColorScheme = lightColorScheme(
    primary = PinkPrimary,
    onPrimary = Color.White,

    primaryContainer = PinkContainer,
    onPrimaryContainer = Color.Black,

    secondary = LavenderSecondary,
    onSecondary = Color.White,

    secondaryContainer = LavenderContainer,
    onSecondaryContainer = Color.Black,

    background = SurfaceSoft,
    onBackground = Color.Black,

    surface = SurfaceSoft,
    onSurface = Color.Black,

    error = ErrorSoft,
    onError = Color.White
)

// =============================
//  COLOR SCHEME DARK
//  (Miya mode: tetap cerah / pink → TEKS HARUS HITAM)
// =============================
private val DarkColorScheme = darkColorScheme(
    primary = PinkContainer,
    onPrimary = Color.Black,

    primaryContainer = PinkPrimary,
    onPrimaryContainer = Color.Black,      // ⭐ TULISAN DI CARD PINK TETAP HITAM

    secondary = LavenderContainer,
    onSecondary = Color.Black,

    secondaryContainer = LavenderSecondary,
    onSecondaryContainer = Color.Black,    // ⭐ TULISAN DI CARD LAVENDER HITAM

    background = SurfaceDarkSoft,
    onBackground = Color.Black,            // ⭐ TULISAN PADA BACKGROUND GELAP → HITAM

    surface = SurfaceDarkSoft,
    onSurface = Color.Black,               // ⭐ TEKS DI DALAM SURFACE → HITAM

    error = ErrorSoft,
    onError = Color.Black
)

// =============================
//  FONT HANDLER
// =============================
@Composable
fun getFontFamily(fontChoice: String): FontFamily {
    return when (fontChoice) {
        "Monospace" -> FontFamily.Monospace
        "Serif" -> FontFamily.Serif
        "Cursive" -> FontFamily.Cursive
        "SansSerif" -> FontFamily.SansSerif
        else -> FontFamily.Default
    }
}

// =============================
//  MIYA THEME FINAL
// =============================
@Composable
fun MiyaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    fontChoice: String = "Default",
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val fontFamily = getFontFamily(fontChoice)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography.applyFontFamily(fontFamily),
        content = content
    )
}
