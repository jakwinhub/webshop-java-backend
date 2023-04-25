package com.demoprojekt.webshop.entity

import com.demoprojekt.webshop.model.OrderStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class OrderEntity(
        @Id val id: String,
        val customerId: String,
        val orderTime: LocalDateTime,

        @Enumerated(EnumType.STRING)
        val status: OrderStatus
)