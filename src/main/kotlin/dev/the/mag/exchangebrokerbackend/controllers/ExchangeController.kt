package dev.the.mag.exchangebrokerbackend.controllers

import dev.the.mag.exchangebrokerbackend.annotations.Authenticated
import dev.the.mag.exchangebrokerbackend.dto.ExchangeDto
import dev.the.mag.exchangebrokerbackend.dto.ExchangeParticipantDto
import dev.the.mag.exchangebrokerbackend.exceptions.NoSuchExchangeException
import dev.the.mag.exchangebrokerbackend.exceptions.TooManyExchanges
import dev.the.mag.exchangebrokerbackend.models.toDto
import dev.the.mag.exchangebrokerbackend.request.RequestExchange
import dev.the.mag.exchangebrokerbackend.services.ExchangeService
import dev.the.mag.exchangebrokerbackend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// TODO: Remove all admin filter stuff - move to separate file / route

@RequestMapping("/api/exchange")
@RestController
class ExchangeController(
    @Autowired
    private val exchangeService: ExchangeService,
    @Autowired
    private val userService: UserService

) {

    @PostMapping("/join/{exchangeCode}")
    @Authenticated
    fun joinExchange(@PathVariable exchangeCode: Int, @RequestHeader username: String): ExchangeParticipantDto {
        val user = userService.getUserByUsername(username)

        return exchangeService.joinExchange(exchangeCode, user.id)
    }

    @PostMapping("")
    @Authenticated
    fun createExchange(@RequestHeader username: String, @RequestBody exchange: RequestExchange): ResponseEntity<ExchangeDto> {

        val user = userService.getUserByUsername(username)
        val activeExchanges = exchangeService.getActiveExchangesByUser(user.id).size

        if (activeExchanges == 1 && !user.admin) {
            throw TooManyExchanges()
        }

        return ResponseEntity(exchangeService.createExchange(exchange.name, user.id, exchange.openDate, exchange.closeDate), HttpStatus.OK)
    }

    @DeleteMapping("/{exchangeId}")
    @Authenticated
    fun deleteExchange(@RequestHeader username: String, @PathVariable exchangeId: Long): ResponseEntity<Boolean> {

        val user = userService.getUserByUsername(username)
        val exchange = exchangeService.getExchange(exchangeId)

        if (exchange.ownerId != user.id && !user.admin) {
            throw NoSuchExchangeException()
        }
        exchangeService.deleteExchange(exchangeId)
        return ResponseEntity(true, HttpStatus.OK)
    }

    @GetMapping("/{exchangeId}")
    @Authenticated
    fun getExchange(@RequestHeader username: String, @PathVariable exchangeId: Long): ResponseEntity<ExchangeDto> {

        val user = userService.getUserByUsername(username)
        val exchange = exchangeService.getExchange(exchangeId)

        if (exchange.ownerId != user.id && !user.admin) {
            throw NoSuchExchangeException()
        }

        return ResponseEntity(exchange.toDto(), HttpStatus.OK)
    }

    @GetMapping("/all")
    @Authenticated
    fun getAllExchanges(@RequestHeader username: String): ResponseEntity<List<ExchangeDto>> {

        val user = userService.getUserByUsername(username)

        return if (user.admin) {
            ResponseEntity(exchangeService.getAllExchanges().map { it.toDto() }, HttpStatus.OK)
        } else {
            ResponseEntity(exchangeService.getAllExchangesByUser(user.id).map { it.toDto() }, HttpStatus.OK)
        }
    }
}
