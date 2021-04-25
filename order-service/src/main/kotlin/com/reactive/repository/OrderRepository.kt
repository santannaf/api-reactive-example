package com.reactive.repository

import com.entity.Order
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface OrderRepository : ReactiveMongoRepository<Order, ObjectId>