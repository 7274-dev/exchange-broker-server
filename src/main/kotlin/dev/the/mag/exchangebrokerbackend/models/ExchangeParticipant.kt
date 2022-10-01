package dev.the.mag.exchangebrokerbackend.models

import dev.the.mag.exchangebrokerbackend.dto.ExchangeParticipantDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

// seller, the kid
@Entity
data class ExchangeParticipant(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var userId: Long,
    var exchangeId: Long,

)

fun ExchangeParticipant.toDto(): ExchangeParticipantDto {
    return ExchangeParticipantDto(this.id, this.userId, this.exchangeId)
}
