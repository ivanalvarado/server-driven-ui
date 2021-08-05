package com.ivanalvarado.serverdrivenui.api.adapter

import androidx.compose.ui.graphics.Color
import com.google.common.truth.Truth.assertThat
import com.ivanalvarado.serverdrivenui.api.model.ThemeResponse
import com.ivanalvarado.serverdrivenui.ui.theme.DesignSystemColors
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

private typealias ColorApi = com.ivanalvarado.serverdrivenui.api.model.Color

@RunWith(Parameterized::class)
class AdaptToDesignSystemColorsTest(
    private val givenThemeResponse: ThemeResponse,
    private val expectedDesignSystemColors: DesignSystemColors
) {

    private val adaptToDesignSystemColors = AdaptToDesignSystemColors()

    @Test
    fun `invoke - given ThemeResponse, should adapt to expected DesignSystemColors`() {
        // When
        val result = adaptToDesignSystemColors(givenThemeResponse)

        // Then
        assertThat(result).isEqualTo(expectedDesignSystemColors)
    }

    companion object {
        @Parameters(
            name = "{index}: given - {1}, expected - {2}"
        )
        @JvmStatic
        fun data(): Iterable<Array<*>> {
            return listOf(
                testCase(
                    givenThemeResponse = ThemeResponse(
                        primary = null,
                        secondary = null,
                        background = null,
                        surface = null,
                        onPrimary = null,
                        onBackground = null,
                        onSurface = null
                    ),
                    expectedDesignSystemColors = DesignSystemColors(
                        primary = Color(-1),
                        primaryVariant = Color(-1),
                        secondary = Color(-1),
                        background = Color(-1),
                        surface = Color(-1),
                        onPrimary = Color(-1),
                        onSecondary = Color(-1),
                        onBackground = Color(-1),
                        onSurface = Color(-1)
                    )
                ),
                testCase(
                    givenThemeResponse = ThemeResponse(
                        primary = ColorApi(value = 1),
                        secondary = ColorApi(value = 2),
                        background = ColorApi(value = 3),
                        surface = ColorApi(value = 4),
                        onPrimary = ColorApi(value = 5),
                        onBackground = ColorApi(value = 6),
                        onSurface = ColorApi(value = 7)
                    ),
                    expectedDesignSystemColors = DesignSystemColors(
                        primary = Color(1),
                        primaryVariant = Color.White,
                        secondary = Color(2),
                        background = Color(3),
                        surface = Color(4),
                        onPrimary = Color(5),
                        onSecondary = Color.White,
                        onBackground = Color(6),
                        onSurface = Color(7)
                    )
                )
            )
        }

        private fun testCase(
            givenThemeResponse: ThemeResponse,
            expectedDesignSystemColors: DesignSystemColors
        ): Array<Any?> {
            return arrayOf(givenThemeResponse, expectedDesignSystemColors)
        }
    }
}
