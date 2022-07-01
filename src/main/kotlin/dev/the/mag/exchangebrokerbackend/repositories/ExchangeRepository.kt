package dev.the.mag.exchangebrokerbackend.repositories

import dev.the.mag.exchangebrokerbackend.models.Exchange
import org.springframework.data.jpa.repository.JpaRepository

interface ExchangeRepository : JpaRepository<Exchange, Long> {
}