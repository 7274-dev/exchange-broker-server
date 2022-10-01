package dev.the.mag.exchangebrokerbackend.exceptions

open class UserException(message: String) : Exception(message)

class NoSuchUserException : UserException("No such user found")
