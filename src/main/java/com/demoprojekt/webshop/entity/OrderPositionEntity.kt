package com.demoprojekt.webshop.entity

import jakarta.persistence.Embeddable


@Embeddable
data class OrderPositionEntity(
        val id: String,
        val orderId: String,
        val productId: String,
        val quantity: Long
)
