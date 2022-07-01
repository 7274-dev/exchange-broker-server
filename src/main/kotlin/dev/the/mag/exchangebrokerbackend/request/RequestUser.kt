package dev.the.mag.exchangebrokerbackend.request

data class RequestUser (
    var username: String,
    var password: String,
    var email: String?
)