package dev.the.mag.exchangebrokerbackend.models

import dev.the.mag.exchangebrokerbackend.dto.ExchangeItemDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ExchangeItem (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var ownerId: Long,
    var exchangeId: Long,

    var name: String,
    var desc: String,
    var price: Double,

    var soldFor: Double?,
    var pending: Boolean?,

    var earningPercent: Int,
    var giveToCharity: Boolean,

    var recall: Boolean,
)

fun ExchangeItem.toDto(): ExchangeItemDto {
    return ExchangeItemDto(ownerId, exchangeId, name, desc, price, soldFor, pending, earningPercent, giveToCharity, recall)
}