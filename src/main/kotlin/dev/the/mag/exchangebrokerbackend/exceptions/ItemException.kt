package dev.the.mag.exchangebrokerbackend.exceptions

open class ItemException(message: String) : Exception(message)

class NoSuchItemException : ItemException("No such item")