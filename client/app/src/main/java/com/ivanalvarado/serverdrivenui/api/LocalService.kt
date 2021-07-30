package com.ivanalvarado.serverdrivenui.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocalService {
    @GET("/theme/{theme}")
    suspend fun getTheme(@Path("theme") theme: String): Response<ThemeResponse>
}
