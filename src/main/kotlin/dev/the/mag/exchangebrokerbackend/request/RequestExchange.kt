package dev.the.mag.exchangebrokerbackend.request

import java.util.Date

data class RequestExchange (
    val name: String,
    val openDate: Date,
    val closeDate: Date
)