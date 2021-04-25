package com.reactive.service

import com.entity.Product
import org.springframework.stereotype.Service
import com.reactive.repository.ProductRepository
import reactor.core.publisher.Flux

@Service
class ProductService(
    private val repository: ProductRepository
) {
    fun getProducts(): Flux<Product> {
        return repository.findAll()
    }
}