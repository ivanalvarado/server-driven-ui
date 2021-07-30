package com.ivanalvarado.serverdrivenui.api

import com.ivanalvarado.serverdrivenui.model.ThemeResult
import com.ivanalvarado.serverdrivenui.ui.theme.DesignSystemColors
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * When accessing a local server through an emulator, the URL is different. If you want to test
 * this on a real device, switch out the base URL here.
 */
private const val BASE_URL_EMULATOR = "http://10.0.2.2:8080/"
private const val BASE_URL_DEVICE = "http://0.0.0.0:8080/"

object LocalClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_DEVICE)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api: LocalService = retrofit.create(LocalService::class.java)
    private val adaptToDesignSystemColors = AdaptToDesignSystemColors()

    suspend fun getTheme(requestedTheme: String): ThemeResult {
        val response = api.getTheme(requestedTheme)
        return if (response.isSuccessful) {
            response.body()?.let {
                ThemeResult.Success(theme = adaptToDesignSystemColors(it))
            } ?: ThemeResult.Failure
        } else {
            ThemeResult.Failure
        }
    }
}
