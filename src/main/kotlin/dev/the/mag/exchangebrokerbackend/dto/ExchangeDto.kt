package dev.the.mag.exchangebrokerbackend.dto

import java.time.LocalDate

data class ExchangeDto (
        var id: Long,
        var name: String,
        var openDate: LocalDate,
        var closeDate: LocalDate,
        var code: Int
)