package com.ivanalvarado.designsystem

import java.awt.Color

data class ColorPalette(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val onPrimary: Color,
    val onBackground: Color,
    val onSurface: Color
)

val DarkColorPalette = ColorPalette(
    primary = Green900,
    secondary = Green300,
    background = Gray,
    surface = White150,
    onPrimary = White,
    onBackground = White,
    onSurface = White850
)

val LightColorPalette = ColorPalette(
    primary = Pink100,
    secondary = Pink900,
    background = White,
    surface = White850,
    onPrimary = Gray,
    onBackground = Gray,
    onSurface = Gray
)