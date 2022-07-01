package dev.the.mag.exchangebrokerbackend.dto

data class UserDto(
    val id: Long,
    val username: String,
    val email: String?,
    val admin: Boolean
)