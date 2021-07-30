package com.ivanalvarado.serverdrivenui.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocalService {
    @GET("/theme/{requested_theme}")
    suspend fun getTheme(
        @Path("requested_theme") requestedTheme: String
    ): Response<ThemeResponse>
}
