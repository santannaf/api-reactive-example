package com.reactive.service

import com.async.producer.OrderProducer
import com.constants.OrderStatus
import com.entity.Order
import com.reactive.repository.OrderRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.stream.Collectors

@Service
class OrderService(
    private val repository: OrderRepository,
    private val producer: OrderProducer
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun createOrder(order: Order): Mono<Order> {
        log.info("Create order invoked with: $order")
        return Mono.just(order)
            .map {
                it.lineItems = it.lineItems.stream().filter { l -> l.quantity > 0 }.collect(Collectors.toList())
                it
            }
            .flatMap(repository::save)
            .map {
                with(it) {
                    orderStatus = OrderStatus.INITIATION_SUCCESS
                }
                producer.sendMessage(it)
                it
            }
            .onErrorResume { err ->
                with(order) {
                    orderStatus = OrderStatus.FAILURE
                    responseMessage = err.message.toString()
                }
                Mono.just(order)
            }
            .flatMap(repository::save)
    }

    fun getOrders(): Flux<Order> {
        log.info("Get all orders invoked.")
        return repository.findAll()
    }
}