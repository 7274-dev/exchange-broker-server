package dev.the.mag.exchangebrokerbackend.controllers

import dev.the.mag.exchangebrokerbackend.annotations.Authenticated
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

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

    @GetMapping("/admin")
    @Authenticated(admin = true)
    fun admin(): String {
        return "admined"
    }

    @GetMapping("/date")
    fun date(): ResponseEntity<Date> {
        val date = LocalDate.now() as Date
        return ResponseEntity(date, HttpStatus.OK)
    }

}