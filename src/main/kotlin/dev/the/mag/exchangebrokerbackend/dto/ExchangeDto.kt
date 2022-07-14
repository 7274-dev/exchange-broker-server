package dev.the.mag.exchangebrokerbackend.dto

import java.util.Date

data class ExchangeDto (

        var id: Long,
        var name: String,
        var openDate: Date,
        var closeDate: Date,
        var code: Int
        )