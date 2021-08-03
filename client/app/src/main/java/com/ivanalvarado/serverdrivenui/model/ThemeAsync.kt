package com.ivanalvarado.serverdrivenui.model

import com.ivanalvarado.serverdrivenui.ui.theme.DesignSystemColors

sealed class ThemeAsync {
    object Loading : ThemeAsync()
    data class Success(val theme: DesignSystemColors): ThemeAsync()
    object Failure : ThemeAsync()
}
