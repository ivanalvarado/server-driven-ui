package com.ivanalvarado

import com.ivanalvarado.designsystem.DarkColorPalette
import com.ivanalvarado.designsystem.LightColorPalette
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        lightTheme()
        darkTheme()
    }
}

private fun Route.lightTheme() {
    get("/theme/light") {
        call.respond(LightColorPalette)
    }
}

private fun Route.darkTheme() {
    get("/theme/dark") {
        call.respond(DarkColorPalette)
    }
}
