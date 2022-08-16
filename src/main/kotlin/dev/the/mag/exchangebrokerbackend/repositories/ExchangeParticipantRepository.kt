package dev.the.mag.exchangebrokerbackend.repositories

import dev.the.mag.exchangebrokerbackend.models.ExchangeParticipant
import org.springframework.data.jpa.repository.JpaRepository

interface ExchangeParticipantRepository : JpaRepository<ExchangeParticipant, Long> {

    fun getExchangeParticipantByExchangeIdAndUserId(exchangeId: Long, userId: Long): ExchangeParticipant?

    fun getAllByExchangeId(exchangeId: Long): List<ExchangeParticipant>?
}