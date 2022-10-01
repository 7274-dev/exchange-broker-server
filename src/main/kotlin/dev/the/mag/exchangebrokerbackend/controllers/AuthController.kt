package dev.the.mag.exchangebrokerbackend.controllers

import dev.the.mag.exchangebrokerbackend.dto.UserDto
import dev.the.mag.exchangebrokerbackend.models.toDto
import dev.the.mag.exchangebrokerbackend.request.RequestUser
import dev.the.mag.exchangebrokerbackend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/auth")
@RestController
class AuthController(
    @Autowired
    private val userService: UserService
) {
    @PostMapping("/user")
    fun createUser(@RequestBody user: RequestUser): ResponseEntity<UserDto> {
        val user = userService.createUser(user.username, user.password, false, user.email)
        return ResponseEntity(user.toDto(), HttpStatus.OK)
    }

    @PostMapping("/admin")
    fun createAdmin(@RequestBody user: RequestUser): ResponseEntity<UserDto> {
        val user = userService.createUser(user.username, user.password, true, user.email)
        return ResponseEntity(user.toDto(), HttpStatus.OK)
    }
}
