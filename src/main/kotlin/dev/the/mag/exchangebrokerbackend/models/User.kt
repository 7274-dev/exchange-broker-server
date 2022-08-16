package dev.the.mag.exchangebrokerbackend.models

import dev.the.mag.exchangebrokerbackend.dto.UserDto
import javax.persistence.*

@Entity
@Table(name = "exchange_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var username: String,
    var password: String,
    var email: String?,
    var admin: Boolean
)

fun User.toDto(): UserDto {
    return UserDto(
        this.id,
        this.username,
        this.email,
        this.admin
    )
}
