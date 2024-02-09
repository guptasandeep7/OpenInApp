package com.sandeepgupta.openinapp.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun OpenInAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // dynamic color is disabled
    content: @Composable () -> Unit
) {
    val constantColors = Colors(
        Primary, PrimaryVariant, Secondary, SecondaryVariant, Background, Surface,
        Error, Color.White, Color.White, Color.Black, Color.Black, Color.White,
        !isSystemInDarkTheme()
    )

    // same color scheme for light and dark mode
    val colors = if (darkTheme) {
        constantColors
    } else {
        constantColors
    }

    //to change status bar color
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val typography = Typography(
        defaultFontFamily = figTreeFontFamily
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}