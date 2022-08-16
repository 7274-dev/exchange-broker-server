package dev.the.mag.exchangebrokerbackend.models

import dev.the.mag.exchangebrokerbackend.dto.ExchangeDto
import java.util.Date
import javax.persistence.*

// stall
@Entity
data class Exchange(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var name: String,
    var ownerId: Long,
    @Temporal(TemporalType.DATE)
    var openDate: Date,
    @Temporal(TemporalType.DATE)
    var closeDate: Date,

    var code: Int
)

fun Exchange.toDto(): ExchangeDto {
    return ExchangeDto(
        this.id,
        this.name,
        this.openDate,
        this.closeDate,
        this.code
    )
}