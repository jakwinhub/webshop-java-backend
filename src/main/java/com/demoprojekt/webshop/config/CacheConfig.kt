package com.demoprojekt.webshop.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cache.annotation.EnableCaching;
import java.util.concurrent.TimeUnit


@EnableCaching
@Configuration
class CacheConfig {

    @Bean
    fun cacheManagerCaffeine(): CacheManager {
        val cacheManager = CaffeineCacheManager("productsResponses")
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .expireAfterAccess(1, TimeUnit.MINUTES)
        )

        return cacheManager
    }
}