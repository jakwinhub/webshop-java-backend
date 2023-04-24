package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.model.OrderCreateRequest
import com.demoprojekt.webshop.model.OrderPositionResponse
import com.demoprojekt.webshop.model.OrderResponse
import com.demoprojekt.webshop.model.OrderStatus
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

/*@Service
open class OrderRepository {

    private val orders = mutableListOf<OrderResponse>()

    fun save(orderResponse: OrderResponse): OrderResponse {

        orders.add(orderResponse)
        return orderResponse
    }

    fun findById(orderId: String): OrderResponse? {
        return orders.find { it.id == orderId }
    }

    fun findAllByCustomerIdWhereOrderStatusIsNew(customerId: String): List<OrderResponse> {
        return orders.filter { it.customerId == customerId && it.status == OrderStatus.NEW }
    }

}
*/

interface OrderRepository : JpaRepository<OrderEntity, String> {
    @Query("SELECT e FROM OrderEntity e where e.status = 'new' and e.customerId = :customerId")
    abstract fun findAllByCustomerIdWhereOrderStatusIsNew(customerId: String): List<OrderEntity>
}

@Entity
@Table(name = "orders")
data class OrderEntity(
        @Id val id: String,
        val customerId: String,
        val orderTimer: LocalDateTime,

        @Enumerated(EnumType.STRING)
        val status: OrderStatus
)