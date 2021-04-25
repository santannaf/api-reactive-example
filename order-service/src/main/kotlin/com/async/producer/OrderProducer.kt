package com.async.producer

import com.entity.Order
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, Order>
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun sendMessage(order: Order) {
        log.info("Order processed to dispatch: $order")
        this.kafkaTemplate.send("orders", order)
    }
}