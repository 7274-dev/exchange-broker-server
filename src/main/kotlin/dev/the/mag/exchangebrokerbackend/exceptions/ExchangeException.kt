package dev.the.mag.exchangebrokerbackend.exceptions

open class ExchangeException(message: String) : Exception(message)

class NoSuchExchangeException : ExchangeException("No such exchange")
class TooManyExchanges : ExchangeException("You have too many exchanges currently running")
