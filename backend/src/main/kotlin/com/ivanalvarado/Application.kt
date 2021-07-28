package com.ivanalvarado

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    routing {
        get("/") {
            call.respondText("Hello, from home!")
        }

        newRoute()
    }
}

private fun Route.newRoute() {
    get("/hello") {
        call.respondText("Hello, from new route")
    }
}
