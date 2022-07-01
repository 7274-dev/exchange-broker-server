package dev.the.mag.exchangebrokerbackend.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ExcahngeParticipant (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        var userId: Long,
        var exchangeId: Long,
        var stackId: Long,
)
