package dev.the.mag.exchangebrokerbackend.exceptionhandler

import dev.the.mag.exchangebrokerbackend.exceptions.NoSuchExchangeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExchangeExceptionHandler {

    @ExceptionHandler(NoSuchExchangeException::class)
    fun handleNoSuchExchangeException(e: NoSuchExchangeException): ResponseEntity<String> {
        return ResponseEntity(e.message ?: "", HttpStatus.NOT_FOUND)
    }

}