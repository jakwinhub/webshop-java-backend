package com.demoprojekt.webshop.model

import java.time.LocalDateTime

data class OrderResponse(
        val id: String,
        val customerId: String,
        val orderTimer: LocalDateTime,
        val status: OrderStatus,
        val orderPositions: List<OrderPositionResponse>
)

enum class OrderStatus {
    NEW, CONFIRMED, DENT, DELIVERED, CANCELED
}

data class OrderPositionResponse(
        val id: String,
        val orderId: String,
        val productId: String,
        val quantity: Long
)