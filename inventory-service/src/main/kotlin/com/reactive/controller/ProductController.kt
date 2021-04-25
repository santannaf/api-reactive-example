package com.reactive.controller

import com.entity.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.reactive.service.ProductService
import reactor.core.publisher.Flux

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/products")
class ProductController(
    private val service: ProductService
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun getAllProducts(): Flux<Product> {
        log.info("Get all products invoked.")
        return service.getProducts()
    }
}