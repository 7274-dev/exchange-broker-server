package dev.the.mag.exchangebrokerbackend.repositories

import dev.the.mag.exchangebrokerbackend.models.ExchangeItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ExchangeItemRepository : JpaRepository<ExchangeItem, Long> {

    fun findAllByExchangeId(exchangeId: Long): List<ExchangeItem>?
    @Query("SELECT e FROM ExchangeItem e WHERE e.exchangeId = ?1")
    fun findAllBySoldAndExchangeId(exchangeId: Long): List<ExchangeItem>?

    @Query("SELECT e FROM ExchangeItem e WHERE e.exchangeId = ?1 AND e.ownerId = ?2")
    fun findAllBySoldAndExchangeIdAndOwnerId(exchangeId: Long, ownerId: Long): List<ExchangeItem>?

}