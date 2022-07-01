package dev.the.mag.exchangebrokerbackend.interceptors

import dev.the.mag.exchangebrokerbackend.annotations.Authenticated
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod)
            return true

        fun fuckYou(): Boolean {
            response.status = 401
            return false
        }

        val needsAuth: Authenticated = handler.getMethodAnnotation(Authenticated::class.java) ?: return true

        logger.info("AuthInterceptor preHandle")
        val username = request.getHeader("username") ?: return fuckYou()
        val password = request.getHeader("password") ?: return fuckYou()
        logger.info("Passed AuthInterceptor with {} and {} ({})", username, password, needsAuth.admin)

        return true
    }
}