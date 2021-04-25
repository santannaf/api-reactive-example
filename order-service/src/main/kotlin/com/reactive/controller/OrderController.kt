package com.reactive.controller

import com.constants.OrderStatus
import com.entity.Order
import com.reactive.service.OrderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/orders")
class OrderController(
    private val service: OrderService
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun create(@RequestBody order: Order): Mono<Order> {
        log.info("Create order invoked with: {}", order)
        return service.createOrder(order)
            .flatMap { o ->
                if (OrderStatus.FAILURE == o.orderStatus) {
                    Mono.error(RuntimeException("Order processing failed, please try again later. ${o.responseMessage}"))
                } else {
                    Mono.just(o)
                }
            }
    }

    @GetMapping
    fun getAll(): Flux<Order> {
        log.info("Get all orders invoked.")
        return service.getOrders()
    }
}