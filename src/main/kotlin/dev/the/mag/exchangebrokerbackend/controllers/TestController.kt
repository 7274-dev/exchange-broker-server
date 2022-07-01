package dev.the.mag.exchangebrokerbackend.controllers

import dev.the.mag.exchangebrokerbackend.annotations.Authenticated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/test")
    fun test(): String {
        return "tested"
    }

    @GetMapping("/auth")
    @Authenticated
    fun auth(): String {
        return "authenticated"
    }
}