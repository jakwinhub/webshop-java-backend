package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.model.OrderPositionResponse
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.core.annotation.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
/*class OrderPositionRepository {

    private val orderPositions = mutableListOf<OrderPositionResponse>()

    fun save(orderPositionResponse: OrderPositionResponse) {
        orderPositions.add(orderPositionResponse)
    }

    fun findAllByOrderIds(orderIds: List<String>): List<OrderPositionResponse> {
       return orderPositions.filter { orderIds.contains(it.orderId) }
    }

}*/
interface OrderPositionRepository : JpaRepository<OrderPositionEntity, String> {

}


@Entity
@Table(name = "order_positions")
data class OrderPositionEntity(
        @Id val id: String,
        val orderId: String,
        val productId: String,
        val quantity: Long
)
