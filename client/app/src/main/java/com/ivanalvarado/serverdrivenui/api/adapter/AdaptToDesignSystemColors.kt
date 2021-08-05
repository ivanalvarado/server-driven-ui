package com.ivanalvarado.serverdrivenui.api.adapter

import androidx.compose.ui.graphics.Color
import com.ivanalvarado.serverdrivenui.api.model.ThemeResponse
import com.ivanalvarado.serverdrivenui.ui.theme.DesignSystemColors

private typealias ColorApi = com.ivanalvarado.serverdrivenui.api.model.Color

internal class AdaptToDesignSystemColors {
    operator fun invoke(themeResponse: ThemeResponse): DesignSystemColors {
        return DesignSystemColors(
            primary = Color(themeResponse.primary.getValue()),
            primaryVariant = Color.White,
            secondary = Color(themeResponse.secondary.getValue()),
            background = Color(themeResponse.background.getValue()),
            surface = Color(themeResponse.surface.getValue()),
            onPrimary = Color(themeResponse.onPrimary.getValue()),
            onSecondary = Color.White,
            onBackground = Color(themeResponse.onBackground.getValue()),
            onSurface = Color(themeResponse.onSurface.getValue())
        )
    }

    private fun ColorApi?.getValue(): Int = this?.value ?: -1
}
