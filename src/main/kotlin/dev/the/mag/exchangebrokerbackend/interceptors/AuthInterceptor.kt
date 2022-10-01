package dev.the.mag.exchangebrokerbackend.interceptors

import dev.the.mag.exchangebrokerbackend.annotations.Authenticated
import dev.the.mag.exchangebrokerbackend.exceptions.AccessDenied
import dev.the.mag.exchangebrokerbackend.exceptions.AuthInvalid
import dev.the.mag.exchangebrokerbackend.exceptions.AuthMissing
import dev.the.mag.exchangebrokerbackend.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(
    @Autowired
    private val userService: UserService
) : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod)
            return true

        val needsAuth: Authenticated = handler.getMethodAnnotation(Authenticated::class.java) ?: return true

        val username = request.getHeader("username") ?: throw AuthMissing()
        val password = request.getHeader("password") ?: throw AuthMissing()

        val user = userService.getUser(username, password) ?: throw AuthInvalid()
        if (needsAuth.admin && !user.admin) throw AccessDenied()

        return true
    }
}
