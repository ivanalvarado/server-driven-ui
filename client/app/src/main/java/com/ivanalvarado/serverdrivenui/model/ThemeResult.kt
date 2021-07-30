package com.ivanalvarado.serverdrivenui.model

import com.ivanalvarado.serverdrivenui.ui.theme.DesignSystemColors

sealed class ThemeResult {
    data class Success(val theme: DesignSystemColors): ThemeResult()
    object Failure : ThemeResult()
}
