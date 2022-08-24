package dev.the.mag.exchangebrokerbackend.repositories

import dev.the.mag.exchangebrokerbackend.models.Exchange
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.Date

interface ExchangeRepository : JpaRepository<Exchange, Long> {
    fun getExchangeByCode(code: Int): Exchange?
    fun getExchangesByOwnerIdAndCloseDateIsBefore(ownerId: Long, closeDate: LocalDate): List<Exchange>

    fun getExchangesByOwnerId(ownerId: Long): List<Exchange>
}