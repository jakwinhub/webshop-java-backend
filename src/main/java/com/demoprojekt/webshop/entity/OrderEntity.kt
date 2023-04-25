package com.demoprojekt.webshop.entity

import com.demoprojekt.webshop.model.OrderStatus
import com.demoprojekt.webshop.repository.OrderPositionEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class OrderEntity(
        @Id val id: String,
        val customerId: String,
        val orderTime: LocalDateTime,

        @Enumerated(EnumType.STRING)
        val status: OrderStatus,

        @ElementCollection
        val orderPositions: List<OrderPositionEntity>
)