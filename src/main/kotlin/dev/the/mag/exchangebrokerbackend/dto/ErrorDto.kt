package dev.the.mag.exchangebrokerbackend.dto

import org.springframework.http.HttpStatus
import java.sql.Timestamp

data class ErrorDto(
    val status: HttpStatus,
    val error: String,
    val message: String,
    val timestamp: Timestamp = Timestamp(System.currentTimeMillis())
)
