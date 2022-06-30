package dev.the.mag.exchangebrokerbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExchangeBrokerBackendApplication

fun main(args: Array<String>) {
    runApplication<ExchangeBrokerBackendApplication>(*args)
}
