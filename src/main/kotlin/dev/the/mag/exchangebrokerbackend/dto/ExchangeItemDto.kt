package dev.the.mag.exchangebrokerbackend.dto


data class ExchangeItemDto (
    var ownerId: Long,
    var exchangeId: Long,

    var name: String,
    var desc: String,
    var price: Int,

    var soldFor: Int?,
    var pending: Boolean?,

    var earningPercent: Int,
    var giveToCharity: Boolean,

    var recall: Boolean,
)

