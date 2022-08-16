package dev.the.mag.exchangebrokerbackend.services

import dev.the.mag.exchangebrokerbackend.dto.ExchangeDto
import dev.the.mag.exchangebrokerbackend.exceptions.NoSuchExchangeException
import dev.the.mag.exchangebrokerbackend.models.Exchange
import dev.the.mag.exchangebrokerbackend.models.ExchangeParticipant
import dev.the.mag.exchangebrokerbackend.models.toDto
import dev.the.mag.exchangebrokerbackend.repositories.ExchangeItemRepository
import dev.the.mag.exchangebrokerbackend.repositories.ExchangeParticipantRepository
import dev.the.mag.exchangebrokerbackend.repositories.ExchangeRepository
import dev.the.mag.exchangebrokerbackend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Date

@Service
class ExchangeService (
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val exchangeRepository: ExchangeRepository,
    @Autowired
    private val exchangeItemRepository: ExchangeItemRepository,
    @Autowired
    private val exchangeParticipantRepository: ExchangeParticipantRepository,
        ) {

    fun createExchange(name: String, ownerId: Long, openDate: Date, closeDate: Date): ExchangeDto {

        var code = (100000..999999).random()

        while (exchangeRepository.getExchangeByCode(code) != null) {
            code = (100000..999999).random()
        }

        var exchange = Exchange(-1, name, ownerId, openDate, closeDate, code)
        exchangeRepository.save(exchange)
        return exchange.toDto()
    }

    fun getExchange(exchangeId: Long): ExchangeDto {
        var exchange = exchangeRepository.getReferenceById(exchangeId)
        return exchange.toDto()
    }

    fun joinExchange(exchangeCode: Int, userId: Long) {
        var exchange = exchangeRepository.getExchangeByCode(exchangeCode) ?: throw NoSuchExchangeException()
        var exchangeParticipant = ExchangeParticipant(-1, userId, exchange.id)
    }
    // What do we want to do when user leaves the exchange and still has unsold items?
    // TODO: Add option to also flag all unsold items for recall
    fun leaveExchange(exchangeId: Long, userId: Long) {
        var exchangeParticipant = exchangeParticipantRepository.getExchangeParticipantByExchangeIdAndUserId(exchangeId, userId) ?: throw NoSuchExchangeException()
        exchangeParticipantRepository.deleteById(exchangeParticipant.id)
    }

    fun deleteExchange(exchangeId: Long) {
        var exchange = exchangeRepository.findByIdOrNull(exchangeId) ?: throw NoSuchExchangeException()
        var participants = exchangeParticipantRepository.getAllByExchangeId(exchangeId)
        var items = exchangeItemRepository.findAllByExchangeId(exchangeId)?.filter { it.soldFor != null }

        exchangeItemRepository.deleteAllInBatch(items ?: listOf())
        exchangeParticipantRepository.deleteAllInBatch(participants ?: listOf())
        exchangeRepository.delete(exchange)
    }

}