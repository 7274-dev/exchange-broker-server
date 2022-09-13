package dev.the.mag.exchangebrokerbackend.services

import dev.the.mag.exchangebrokerbackend.exceptions.NoSuchUserException
import dev.the.mag.exchangebrokerbackend.models.User
import dev.the.mag.exchangebrokerbackend.repositories.ExchangeRepository
import dev.the.mag.exchangebrokerbackend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val exchangeRepository: ExchangeRepository
) {

    // TODO: Add hashing to passwords
    fun createUser(username: String, password: String, isAdmin: Boolean, email: String?): User {
        var user = User(-1, username, password, email, isAdmin)
        userRepository.save(user)
        return user
    }

    fun getUser(username: String, password: String): User? {
        return userRepository.findByUsernameAndPassword(username, password)
    }

    fun editUser(userId: Long, username: String?, password: String?, email: String?): User {

        var user = userRepository.findByIdOrNull(userId) ?: throw NoSuchUserException()

        if (username != null) {
            user.username = username
        }

        if (password != null) {
            user.password = password
        }

        if (email != null) {
            user.email = email
        }

        userRepository.save(user)
        return user
    }

    fun deleteUser(userId: Long): Boolean {
        var user = userRepository.findByIdOrNull(userId) ?: throw NoSuchUserException()
        userRepository.delete(user)
        return true
    }

    fun getUserByUsername(username: String): User {
        return userRepository.findUserByUsername(username)
    }
}
