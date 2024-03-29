package dev.the.mag.exchangebrokerbackend.exceptionhandler

import dev.the.mag.exchangebrokerbackend.exceptions.NoSuchUserException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserExceptionHandler {
    @ExceptionHandler(NoSuchUserException::class)
    fun handleUserNotFound(e: NoSuchUserException): ResponseEntity<String> {
        return ResponseEntity(e.message ?: "", HttpStatus.NOT_FOUND)
    }
}
