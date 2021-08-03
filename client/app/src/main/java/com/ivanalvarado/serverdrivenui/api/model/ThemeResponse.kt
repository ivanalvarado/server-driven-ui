package com.ivanalvarado.serverdrivenui.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThemeResponse(
    @Json(name = "primary") val primary: Color,
    @Json(name = "secondary") val secondary: Color,
    @Json(name = "background") val background: Color,
    @Json(name = "surface") val surface: Color,
    @Json(name = "onPrimary") val onPrimary: Color,
    @Json(name = "onBackground") val onBackground: Color,
    @Json(name = "onSurface") val onSurface: Color
)

data class Color(
    @Json(name = "value") val value: Int
)
