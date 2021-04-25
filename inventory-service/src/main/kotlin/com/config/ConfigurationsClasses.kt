package com.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.ReactiveMongoTransactionManager
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean

@Configuration
class ConfigurationsClasses {
    @Bean
    fun getRespositoryPopulator(): Jackson2RepositoryPopulatorFactoryBean {
        val factory = Jackson2RepositoryPopulatorFactoryBean()
        factory.setResources(arrayOf(ClassPathResource("data.json")))
        return factory
    }

    @Bean
    fun transactionManager(dbFactory: ReactiveMongoDatabaseFactory): ReactiveMongoTransactionManager {
        return ReactiveMongoTransactionManager(dbFactory)
    }
}