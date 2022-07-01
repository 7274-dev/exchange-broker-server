package dev.the.mag.exchangebrokerbackend.exceptionhandler

import dev.the.mag.exchangebrokerbackend.dto.ErrorDto
import dev.the.mag.exchangebrokerbackend.exceptions.AccessDenied
import dev.the.mag.exchangebrokerbackend.exceptions.AuthIncorrect
import dev.the.mag.exchangebrokerbackend.exceptions.AuthMissing
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionHandler {
    @ExceptionHandler(AuthMissing::class)
    fun handleAuthMissing(e: AuthMissing): ErrorDto {
        return ErrorDto(HttpStatus.UNAUTHORIZED, e.message ?: "", e.message ?: "")
    }

    @ExceptionHandler(AuthIncorrect::class)
    fun handleAuthInvalid(e: AuthIncorrect): ErrorDto {
        return ErrorDto(HttpStatus.UNAUTHORIZED, e.message ?: "", e.message ?: "")
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(e: AccessDenied): ErrorDto {
        return ErrorDto(HttpStatus.FORBIDDEN, e.message ?: "", e.message ?: "")
    }
}
