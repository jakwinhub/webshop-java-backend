package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.model.OrderCreateRequest
import com.demoprojekt.webshop.model.OrderResponse
import com.demoprojekt.webshop.model.OrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
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
