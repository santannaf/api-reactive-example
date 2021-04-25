package com.async.consumer

import com.async.producer.OrderProducer
import com.constants.OrderStatus
import com.entity.Order
import com.reactive.repository.OrderRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class OrderConsumer(
    private val repository: OrderRepository,
    private val producer: OrderProducer
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["orders"], groupId = "orders")
    fun consumer(order: Order) {
        log.info("Order received to process: $order")
        val id = order.id.takeIf { it != null } ?: return

        when (order.orderStatus) {
            OrderStatus.INITIATION_SUCCESS -> {
                repository.findById(id)
                    .map {
                        with(it) {
                            orderStatus = OrderStatus.RESERVE_INVENTORY
                        }

                        producer.sendMessage(it)

                        with((it)) {
                            orderStatus = order.orderStatus
                            responseMessage = order.responseMessage
                        }
                        it
                    }
                    .flatMap(repository::save)
                    .subscribe()
            }
            else -> {
                repository.findById(id)
                    .map {
                        with(it) {
                            orderStatus = order.orderStatus
                            responseMessage = order.responseMessage
                        }
                        it
                    }
                    .flatMap(repository::save)
                    .subscribe()
            }
        }
    }
}