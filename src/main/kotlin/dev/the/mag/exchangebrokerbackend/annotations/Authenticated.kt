package dev.the.mag.exchangebrokerbackend.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Authenticated(val admin: Boolean = false)
