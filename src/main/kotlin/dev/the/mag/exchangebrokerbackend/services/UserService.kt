package dev.the.mag.exchangebrokerbackend.services

import dev.the.mag.exchangebrokerbackend.models.User
import dev.the.mag.exchangebrokerbackend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService (
    @Autowired
    private val userRepository: UserRepository
    ) {

    fun createNewUser(username: String, password: String, isAdmin: Boolean, email: String?): User {
        var user = User(-1, username, password, email, isAdmin);
        userRepository.save(user);
        return user;
    }

    fun getUser(username: String, password: String): User? {
        return userRepository.findByUsernameAndPassword(username, password)
    }
}
