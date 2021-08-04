package com.ivanalvarado.serverdrivenui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivanalvarado.serverdrivenui.model.Theme
import com.ivanalvarado.serverdrivenui.model.ThemeAsync
import com.ivanalvarado.serverdrivenui.ui.theme.ServerDrivenUITheme
import com.ivanalvarado.serverdrivenui.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ServerDrivenUITheme {
                val designSystemState by viewModel.themeState.collectAsState()

                when (designSystemState) {
                    ThemeAsync.Failure -> {
                        Error(message = getString(R.string.error_fetching_design_system_colors))
                    }
                    ThemeAsync.Loading -> LoadingSpinner()
                    is ThemeAsync.Success -> {
                        val theme = (designSystemState as ThemeAsync.Success).theme
                        Column {
                            ColorRow(color = theme.primary, colorName = "primary")
                            ColorRow(color = theme.primaryVariant, colorName = "primaryVariant")
                            ColorRow(color = theme.secondary, colorName = "secondary")
                            ColorRow(color = theme.background, colorName = "background")
                            ColorRow(color = theme.surface, colorName = "surface")
                            ColorRow(color = theme.onPrimary, colorName = "onPrimary")
                            ColorRow(color = theme.onSecondary, colorName = "onSecondary")
                            ColorRow(color = theme.onBackground, colorName = "onBackground")
                            ColorRow(color = theme.onSurface, colorName = "onSurface")
                        }
                    }
                }

                viewModel.getDesignSystem(Theme.LIGHT)

                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
            }
        }
    }
}

@Composable
fun ColorRow(color: Color, colorName: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(modifier = Modifier
            .background(color = color)
            .size(50.dp, 50.dp))
        Spacer(Modifier.size(12.dp))
        Text(text = colorName)
    }
}

@Composable
fun Error(message: String) {
    Text(text = message)
}

@Composable
fun LoadingSpinner() {
    CircularProgressIndicator()
}

@Preview
@Composable
fun LoadingSpinnerPreview() {
    LoadingSpinner()
}

@Preview
@Composable
fun ColorRowPreview() {
    ColorRow(Color.Blue, "Primary")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ServerDrivenUITheme {
        Error("Failed to fetch design system colors!")
    }
}
