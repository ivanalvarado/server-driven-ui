package com.ivanalvarado.serverdrivenui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanalvarado.serverdrivenui.api.client.LocalClient
import com.ivanalvarado.serverdrivenui.model.Theme
import com.ivanalvarado.serverdrivenui.model.ThemeAsync
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThemeViewModel : ViewModel() {

    val themeState: StateFlow<ThemeAsync> get() = _themeState
    private val _themeState = MutableStateFlow<ThemeAsync>(ThemeAsync.Loading)

    fun getDesignSystem(requestedTheme: Theme) {
        viewModelScope.launch {
            val theme = LocalClient.getTheme(requestedTheme)
            _themeState.emit(theme)
        }
    }
}
