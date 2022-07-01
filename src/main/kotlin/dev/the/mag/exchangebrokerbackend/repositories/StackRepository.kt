package dev.the.mag.exchangebrokerbackend.repositories

import dev.the.mag.exchangebrokerbackend.models.Stack
import org.springframework.data.jpa.repository.JpaRepository

interface StackRepository : JpaRepository<Stack, Long> {
}