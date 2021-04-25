package com.entity

import com.constants.OrderStatus
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.utils.ObjectIdSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Order(
    @Id
    @JsonSerialize(using = ObjectIdSerializer::class)
    val id: ObjectId?,
    val userId: String,
    var lineItems: MutableList<LineItem>,
    val total: Long,
    val paymentMode: String,
    val shippingAddress: Address,
    var orderStatus: OrderStatus?,
    val shippingDate: Date,
    var responseMessage: String?
)