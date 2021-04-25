package com.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.utils.ObjectIdSerializer
import org.bson.types.ObjectId

@JsonIgnoreProperties(ignoreUnknown = true)
data class LineItem(
    @JsonSerialize(using = ObjectIdSerializer::class)
    val productId: ObjectId?,
    val quantity: Int
)