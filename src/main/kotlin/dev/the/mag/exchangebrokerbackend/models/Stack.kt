package dev.the.mag.exchangebrokerbackend.models

import javax.persistence.*

@Entity
data class Stack(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @OneToMany
    var items: Set<ExchangeItem>
)
