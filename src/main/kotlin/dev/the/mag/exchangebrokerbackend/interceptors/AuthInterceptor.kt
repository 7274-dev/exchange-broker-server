package dev.the.mag.exchangebrokerbackend.interceptors

import dev.the.mag.exchangebrokerbackend.annotations.Authenticated
import dev.the.mag.exchangebrokerbackend.exceptions.AuthMissing
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    // TODO: Add checking if user actually exists
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod)
            return true

        val needsAuth: Authenticated = handler.getMethodAnnotation(Authenticated::class.java) ?: return true

        logger.info("AuthInterceptor preHandle")
        val username = request.getHeader("username") ?: throw AuthMissing()
        val password = request.getHeader("password") ?: throw AuthMissing()
        logger.info("Passed AuthInterceptor with {} and {} ({})", username, password, needsAuth.admin)

        return true
    }
}
