package dev.the.mag.exchangebrokerbackend.exceptions

open class AuthException(message: String) : Exception(message)

class AuthMissing : AuthException("Missing auth")
class AuthInvalid : AuthException("Invalid auth")
class AccessDenied : AuthException("Access denied") // insufficient permissions
