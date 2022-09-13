package dev.the.mag.exchangebrokerbackend.exceptionhandler

import dev.the.mag.exchangebrokerbackend.exceptions.AccessDenied
import dev.the.mag.exchangebrokerbackend.exceptions.AuthInvalid
import dev.the.mag.exchangebrokerbackend.exceptions.AuthMissing
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionHandler {
    @ExceptionHandler(AuthMissing::class)
    fun handleAuthMissing(e: AuthMissing): ResponseEntity<String> {
        return ResponseEntity(e.message ?: "", HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(AuthInvalid::class)
    fun handleAuthInvalid(e: AuthInvalid): ResponseEntity<String> {
        return ResponseEntity(e.message ?: "", HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(e: AccessDenied): ResponseEntity<String> {
        return ResponseEntity(e.message ?: "", HttpStatus.FORBIDDEN)
    }
}
