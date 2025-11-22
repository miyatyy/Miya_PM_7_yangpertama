package com.example.miya_pm_7.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

// 🌸 LIGHT THEME — PASTEL AESTHETIC
private val LightColorScheme = lightColorScheme(
    primary = PinkAesthetic,
    onPrimary = Color.White,
    primaryContainer = PinkPastel,
    onPrimaryContainer = PinkAesthetic,

    secondary = PurpleSoft,
    onSecondary = Color.White,
    secondaryContainer = PurpleAccent,
    onSecondaryContainer = Color.White,

    tertiary = PinkSoft,
    onTertiary = PinkAesthetic,

    background = CreamSoft,
    onBackground = Color(0xFF3A3A3A),

    surface = Color.White,
    onSurface = Color(0xFF3A3A3A),

    error = SoftError,
    onError = Color.White
)

// 🌙 DARK THEME — PASTEL DARK
private val DarkColorScheme = darkColorScheme(
    primary = PinkAesthetic,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF4A0033),
    onPrimaryContainer = PinkPastel,

    secondary = PurpleAccent,
    onSecondary = Color.Black,

    tertiary = PinkPastel,
    onTertiary = Color.Black,

    background = Color(0xFF1A1A1A),
    onBackground = Color(0xFFECECEC),

    surface = Color(0xFF232323),
    onSurface = Color(0xFFECECEC),

    error = SoftErrorDark,
    onError = Color.White
)

@Composable
fun MiyaTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (useDarkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Status bar icon color
    val view = LocalView.current
    if (!view.isInEditMode) {
        ViewCompat.getWindowInsetsController(view)
            ?.isAppearanceLightStatusBars = !useDarkTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
