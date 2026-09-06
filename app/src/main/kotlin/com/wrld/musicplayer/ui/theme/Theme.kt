package com.wrld.musicplayer.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkMode
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Light colors
private val md_theme_light_primary = Color(0xFFFF6B3B)
private val md_theme_light_on_primary = Color(0xFFFFFFFF)
private val md_theme_light_primary_container = Color(0xFFFFE8D7)
private val md_theme_light_on_primary_container = Color(0xFF3D1A0B)
private val md_theme_light_secondary = Color(0xFFFF6B3B)
private val md_theme_light_on_secondary = Color(0xFFFFFFFF)
private val md_theme_light_secondary_container = Color(0xFFFFE8D7)
private val md_theme_light_on_secondary_container = Color(0xFF3D1A0B)
private val md_theme_light_tertiary = Color(0xFF986201)
private val md_theme_light_on_tertiary = Color(0xFFFFFFFF)
private val md_theme_light_tertiary_container = Color(0xFFFFE8D7)
private val md_theme_light_on_tertiary_container = Color(0xFF320B00)
private val md_theme_light_error = Color(0xFFB3261E)
private val md_theme_light_on_error = Color(0xFFFFFFFF)
private val md_theme_light_error_container = Color(0xFFF9DEDC)
private val md_theme_light_on_error_container = Color(0xFF410E0B)
private val md_theme_light_background = Color(0xFFFFFBFE)
private val md_theme_light_on_background = Color(0xFF1C1B1F)
private val md_theme_light_surface = Color(0xFFFFFBFE)
private val md_theme_light_on_surface = Color(0xFF1C1B1F)
private val md_theme_light_surface_variant = Color(0xFFF5DDD0)
private val md_theme_light_on_surface_variant = Color(0xFF5F4A45)
private val md_theme_light_outline = Color(0xFF9C8784)

// Dark colors
private val md_theme_dark_primary = Color(0xFFFFCFB2)
private val md_theme_dark_on_primary = Color(0xFF5D2A16)
private val md_theme_dark_primary_container = Color(0xFF7D4228)
private val md_theme_dark_on_primary_container = Color(0xFFFFCFB2)
private val md_theme_dark_secondary = Color(0xFFFFCFB2)
private val md_theme_dark_on_secondary = Color(0xFF5D2A16)
private val md_theme_dark_secondary_container = Color(0xFF7D4228)
private val md_theme_dark_on_secondary_container = Color(0xFFFFCFB2)
private val md_theme_dark_tertiary = Color(0xFFFFB871)
private val md_theme_dark_on_tertiary = Color(0xFF531500)
private val md_theme_dark_tertiary_container = Color(0xFF733A00)
private val md_theme_dark_on_tertiary_container = Color(0xFFFFB871)
private val md_theme_dark_error = Color(0xFFF2B8B5)
private val md_theme_dark_on_error = Color(0xFF601410)
private val md_theme_dark_error_container = Color(0xFF8C1D18)
private val md_theme_dark_on_error_container = Color(0xFFF2B8B5)
private val md_theme_dark_background = Color(0xFF1C1B1F)
private val md_theme_dark_on_background = Color(0xFFE7E0E8)
private val md_theme_dark_surface = Color(0xFF1C1B1F)
private val md_theme_dark_on_surface = Color(0xFFE7E0E8)
private val md_theme_dark_surface_variant = Color(0xFF5F4A45)
private val md_theme_dark_on_surface_variant = Color(0xFFD7BDB4)
private val md_theme_dark_outline = Color(0xFFA08E87)

private val lightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_on_primary,
    primaryContainer = md_theme_light_primary_container,
    onPrimaryContainer = md_theme_light_on_primary_container,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_on_secondary,
    secondaryContainer = md_theme_light_secondary_container,
    onSecondaryContainer = md_theme_light_on_secondary_container,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_on_tertiary,
    tertiaryContainer = md_theme_light_tertiary_container,
    onTertiaryContainer = md_theme_light_on_tertiary_container,
    error = md_theme_light_error,
    onError = md_theme_light_on_error,
    errorContainer = md_theme_light_error_container,
    onErrorContainer = md_theme_light_on_error_container,
    background = md_theme_light_background,
    onBackground = md_theme_light_on_background,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_on_surface,
    surfaceVariant = md_theme_light_surface_variant,
    onSurfaceVariant = md_theme_light_on_surface_variant,
    outline = md_theme_light_outline
)

private val darkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_on_primary,
    primaryContainer = md_theme_dark_primary_container,
    onPrimaryContainer = md_theme_dark_on_primary_container,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_on_secondary,
    secondaryContainer = md_theme_dark_secondary_container,
    onSecondaryContainer = md_theme_dark_on_secondary_container,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_on_tertiary,
    tertiaryContainer = md_theme_dark_tertiary_container,
    onTertiaryContainer = md_theme_dark_on_tertiary_container,
    error = md_theme_dark_error,
    onError = md_theme_dark_on_error,
    errorContainer = md_theme_dark_error_container,
    onErrorContainer = md_theme_dark_on_error_container,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_on_background,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_on_surface,
    surfaceVariant = md_theme_dark_surface_variant,
    onSurfaceVariant = md_theme_dark_on_surface_variant,
    outline = md_theme_dark_outline
)

@Composable
fun WrldTheme(
    darkTheme: Boolean = isSystemInDarkMode(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars =
                !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
