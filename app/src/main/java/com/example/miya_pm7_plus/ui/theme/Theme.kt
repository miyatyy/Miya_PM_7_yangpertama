package com.example.miya_pm7_plus.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

@Composable
fun MiyaTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val lightColors = lightColorScheme(
        primary = GoldPrimary,
        onPrimary = OnGoldPrimary,
        primaryContainer = GoldContainer,
        onPrimaryContainer = OnGoldContainer,

        secondary = ChampagneSoft,
        onSecondary = OnGoldPrimary,
        secondaryContainer = Champagne,

        tertiary = Champagne,
        onTertiary = OnGoldPrimary,

        background = SurfaceLight,
        onBackground = OnSurfaceLight,

        surface = SurfaceLight,
        onSurface = OnSurfaceLight,

        error = ErrorSoft,
        onError = SurfaceLight
    )

    val darkColors = darkColorScheme(
        primary = GoldPrimaryDark,
        onPrimary = OnGoldPrimary,
        primaryContainer = SurfaceCard,
        onPrimaryContainer = GoldContainer,

        secondary = Charcoal,
        onSecondary = SurfaceLight,
        secondaryContainer = SurfaceCard,

        background = SurfaceDark,
        onBackground = SurfaceLight,

        surface = SurfaceCard,
        onSurface = SurfaceLight,

        error = ErrorSoft,
        onError = SurfaceLight
    )

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        useDarkTheme -> darkColors
        else -> lightColors
    }

    // status bar icons
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
