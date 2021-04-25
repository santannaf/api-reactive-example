package com.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.constants.OrderStatus
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
data class Order(
    val id: ObjectId,
    val userId: String,
    val lineItems: MutableList<LineItem>,
    val total: Long,
    val orderStatus: OrderStatus,
    val responseMessage: String
)