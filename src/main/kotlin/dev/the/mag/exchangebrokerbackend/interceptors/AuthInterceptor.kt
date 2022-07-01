package dev.the.mag.exchangebrokerbackend.interceptors

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val logger = LoggerFactory.getLogger(AuthInterceptor::class.java)
        logger.info("AuthInterceptor preHandle")
        return true
    }
}