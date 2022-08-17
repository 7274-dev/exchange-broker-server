package dev.the.mag.exchangebrokerbackend.repositories

import dev.the.mag.exchangebrokerbackend.models.ExchangeItem
import org.springframework.data.jpa.repository.JpaRepository

interface ExchangeItemRepository : JpaRepository<ExchangeItem, Long> {

    fun findAllByExchangeId(exchangeId: Long): List<ExchangeItem>?
}