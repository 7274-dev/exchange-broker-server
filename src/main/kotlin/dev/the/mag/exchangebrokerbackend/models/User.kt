package dev.the.mag.exchangebrokerbackend.models

import dev.the.mag.exchangebrokerbackend.dto.UserDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var username: String,
    var password: String,
    var email: String?,
    var admin: Boolean
)

fun User.toUserDto(): UserDto {
    return UserDto(
        this.id,
        this.username,
        this.email,
        this.admin
    )
}
