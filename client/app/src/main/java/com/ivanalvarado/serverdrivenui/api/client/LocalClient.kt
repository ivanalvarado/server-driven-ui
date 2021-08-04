package com.ivanalvarado.serverdrivenui.api.client

import com.ivanalvarado.serverdrivenui.api.adapter.AdaptToDesignSystemColors
import com.ivanalvarado.serverdrivenui.api.service.LocalService
import com.ivanalvarado.serverdrivenui.model.Theme
import com.ivanalvarado.serverdrivenui.model.ThemeAsync
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * When accessing a local server through an emulator, the URL is different. If you want to test
 * this on a real device, switch out the base URL here.
 */
private const val BASE_URL_EMULATOR = "http://10.0.2.2:8080/"
private const val BASE_URL_DEVICE = "http://0.0.0.0:8080/"

object LocalClient {

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .callTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_DEVICE)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .client(okHttpClient)
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
