package dev.the.mag.exchangebrokerbackend.controllers

import dev.the.mag.exchangebrokerbackend.dto.UserDto
import dev.the.mag.exchangebrokerbackend.models.User
import dev.the.mag.exchangebrokerbackend.models.toUserDto
import dev.the.mag.exchangebrokerbackend.repositories.UserRepository
import dev.the.mag.exchangebrokerbackend.request.RequestUser
import dev.the.mag.exchangebrokerbackend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController (
    @Autowired
    val userService: UserService
    ) {


    @PostMapping("/signup")
    fun signUp(@RequestBody user: RequestUser): ResponseEntity<UserDto> {
        val createdUser = userService.createNewUser(user.username, user.password, false, user.email);
        return ResponseEntity(createdUser.toUserDto(), HttpStatus.OK)
    }

}