package com.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.bson.types.ObjectId
import com.utils.ObjectIdSerializer

@JsonIgnoreProperties(ignoreUnknown = true)
data class LineItem(
    @JsonSerialize(using = ObjectIdSerializer::class)
    val productId: ObjectId,
    val quantity: Int
)