package dev.the.mag.exchangebrokerbackend.models

import java.util.Date
import javax.persistence.*

// stall
@Entity
data class Exchange(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var ownerId: Long,
    @Temporal(TemporalType.DATE)
    var openDate: Date,
    @Temporal(TemporalType.DATE)
    var closeDate: Date,

    var code: Int
)