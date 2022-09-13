package dev.the.mag.exchangebrokerbackend.models

import dev.the.mag.exchangebrokerbackend.dto.ExchangeDto
import java.time.LocalDate
import javax.persistence.*

// stall
@Entity
data class Exchange(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var name: String,
    var ownerId: Long,

    var openDate: LocalDate,
    var closeDate: LocalDate,

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
