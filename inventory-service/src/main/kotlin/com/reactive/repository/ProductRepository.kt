package com.reactive.repository

import com.entity.Product
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ProductRepository : ReactiveMongoRepository<Product, ObjectId>