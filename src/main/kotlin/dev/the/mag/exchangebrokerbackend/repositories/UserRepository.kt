package dev.the.mag.exchangebrokerbackend.repositories

import dev.the.mag.exchangebrokerbackend.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findUserByUserNameAndPassword(username: String, password: String): User?
}