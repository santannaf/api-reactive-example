package com.entity

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.utils.ObjectIdSerializer
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Product(
    @JsonSerialize(using = ObjectIdSerializer::class)
    val id: String = "",
    val name: String = "",
    val price: Long = 0,
    val stock: Int = 0
)