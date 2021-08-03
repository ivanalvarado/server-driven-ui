package com.ivanalvarado.serverdrivenui.api.client

import com.ivanalvarado.serverdrivenui.api.adapter.AdaptToDesignSystemColors
import com.ivanalvarado.serverdrivenui.api.service.LocalService
import com.ivanalvarado.serverdrivenui.model.Theme
import com.ivanalvarado.serverdrivenui.model.ThemeAsync
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

    suspend fun getTheme(requestedTheme: Theme): ThemeAsync {
        val response = api.getTheme(requestedTheme.value)
        return if (response.isSuccessful) {
            response.body()?.let {
                ThemeAsync.Success(theme = adaptToDesignSystemColors(it))
            } ?: ThemeAsync.Failure
        } else {
            ThemeAsync.Failure
        }
    }
}
