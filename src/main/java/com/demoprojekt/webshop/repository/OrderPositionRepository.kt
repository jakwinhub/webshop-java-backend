package com.demoprojekt.webshop.repository

import jakarta.persistence.Embeddable


@Embeddable
data class OrderPositionEntity(
        val id: String,
        val orderId: String,
        val productId: String,
        val quantity: Long
)
