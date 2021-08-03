package com.ivanalvarado.serverdrivenui.api.adapter

import androidx.compose.ui.graphics.Color
import com.ivanalvarado.serverdrivenui.api.ThemeResponse
import com.ivanalvarado.serverdrivenui.ui.theme.DesignSystemColors

internal class AdaptToDesignSystemColors {
    operator fun invoke(themeResponse: ThemeResponse): DesignSystemColors {
        return DesignSystemColors(
            primary = Color(themeResponse.primary.value),
            primaryVariant = Color.White,
            secondary = Color(themeResponse.secondary.value),
            background = Color(themeResponse.background.value),
            surface = Color(themeResponse.surface.value),
            onPrimary = Color(themeResponse.onPrimary.value),
            onSecondary = Color.White,
            onBackground = Color(themeResponse.onBackground.value),
            onSurface = Color(themeResponse.onSurface.value)
        )
    }
}
