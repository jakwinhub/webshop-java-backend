package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.model.OrderCreateRequest
import com.demoprojekt.webshop.model.OrderResponse
import com.demoprojekt.webshop.model.OrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class OrderRepository {

    private val orders = mutableListOf<OrderResponse>()

    fun save(request: OrderCreateRequest): OrderResponse {

        val orderResponse = OrderResponse(
                id = UUID.randomUUID().toString(),
                customerId = request.customerId,
                orderTimer = LocalDateTime.now(),
                status = OrderStatus.NEW,
                orderPositions = emptyList()
        )

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
