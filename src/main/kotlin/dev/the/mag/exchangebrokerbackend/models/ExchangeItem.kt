package dev.the.mag.exchangebrokerbackend.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ExchangeItem(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var ownerId: Long,
    var exchangeId: Long,
    var name: String,
    var desc: String,
    var price: Int,
    var soldFor: Int?,
    var pending: Boolean,
    var earningPercent: Int,
    var giveToCharity: Boolean
)