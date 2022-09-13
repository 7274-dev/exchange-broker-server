package dev.the.mag.exchangebrokerbackend.services

import dev.the.mag.exchangebrokerbackend.dto.ExchangeDto
import dev.the.mag.exchangebrokerbackend.dto.ExchangeParticipantDto
import dev.the.mag.exchangebrokerbackend.exceptions.NoSuchExchangeException
import dev.the.mag.exchangebrokerbackend.models.Exchange
import dev.the.mag.exchangebrokerbackend.models.ExchangeItem
import dev.the.mag.exchangebrokerbackend.models.ExchangeParticipant
import dev.the.mag.exchangebrokerbackend.models.toDto
import dev.the.mag.exchangebrokerbackend.repositories.ExchangeItemRepository
import dev.the.mag.exchangebrokerbackend.repositories.ExchangeParticipantRepository
import dev.the.mag.exchangebrokerbackend.repositories.ExchangeRepository
import dev.the.mag.exchangebrokerbackend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Service
class ExchangeService(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val exchangeRepository: ExchangeRepository,
    @Autowired
    private val exchangeItemRepository: ExchangeItemRepository,
    @Autowired
    private val exchangeParticipantRepository: ExchangeParticipantRepository,
) {

    private fun getUniqueExchangeCode(): Int {
        var code = (100000..999999).random()

        while (exchangeRepository.getExchangeByCode(code) != null) {
            code = (100000..999999).random()
        }

        return code
    }

    fun createExchange(name: String, ownerId: Long, openDate: Date, closeDate: Date): ExchangeDto {
        val code = getUniqueExchangeCode()

        val opening = openDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val closing = closeDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val exchange = Exchange(
            -1,
            name,
            ownerId,
            opening,
            closing,
            code
        )

        exchangeRepository.save(exchange)

        return exchange.toDto()
    }

    fun getExchange(exchangeId: Long): Exchange {
        return exchangeRepository.getReferenceById(exchangeId)
    }

    fun getExchangeByCode(exchangeCode: Int): Exchange {
        return exchangeRepository.getExchangeByCode(exchangeCode) ?: throw NoSuchExchangeException()
    }

    fun joinExchange(exchangeCode: Int, userId: Long): ExchangeParticipantDto {
        val exchange = exchangeRepository.getExchangeByCode(exchangeCode) ?: throw NoSuchExchangeException()
        val exchangeParticipant = ExchangeParticipant(-1, userId, exchange.id)

        val joinedParticipant = exchangeParticipantRepository.getExchangeParticipantByExchangeIdAndUserId(exchange.id, userId)
            ?: exchangeParticipantRepository.save(exchangeParticipant)

        return joinedParticipant.toDto()
    }

    // What do we want to do when user leaves the exchange and still has unsold items?
    // TODO: Add option to also flag all unsold items for recall
    fun leaveExchange(exchangeId: Long, userId: Long) {
        val exchangeParticipant = exchangeParticipantRepository.getExchangeParticipantByExchangeIdAndUserId(exchangeId, userId) ?: throw NoSuchExchangeException()
        exchangeParticipantRepository.deleteById(exchangeParticipant.id)
    }

    fun deleteExchange(exchangeId: Long) {
        val exchange = exchangeRepository.findByIdOrNull(exchangeId) ?: throw NoSuchExchangeException()
        val participants = exchangeParticipantRepository.getAllByExchangeId(exchangeId)
        val items = exchangeItemRepository.findAllByExchangeId(exchangeId)?.filter { it.soldFor != null }

        exchangeItemRepository.deleteAllInBatch(items ?: listOf())
        exchangeParticipantRepository.deleteAllInBatch(participants ?: listOf())
        exchangeRepository.delete(exchange)
    }

    fun getSoldItemsForExchange(exchangeId: Long): List<ExchangeItem> {
        return exchangeItemRepository.findAllBySoldAndExchangeId(exchangeId) ?: return listOf()
    }

    fun getSoldItemsForExchangeAndUser(exchangeId: Long, userId: Long): List<ExchangeItem> {
        return exchangeItemRepository.findAllBySoldAndExchangeIdAndOwnerId(exchangeId, userId) ?: return listOf()
    }
    fun getActiveExchangesByUser(userId: Long): List<Exchange> {
        val today = LocalDate.now()
        return exchangeRepository.getExchangesByOwnerIdAndCloseDateIsBefore(userId, today)
    }

    fun getAllExchangesByUser(userId: Long): List<Exchange> {
        return exchangeRepository.getExchangesByOwnerId(userId)
    }

    fun getAllExchanges(): List<Exchange> {
        return exchangeRepository.findAll()
    }
}
