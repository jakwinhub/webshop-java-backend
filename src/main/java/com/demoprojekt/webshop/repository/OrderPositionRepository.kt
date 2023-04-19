package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.model.OrderPositionResponse
import org.springframework.stereotype.Service

@Service
class OrderPositionRepository {

    private val orderPositions = mutableListOf<OrderPositionResponse>()

    fun save(orderPositionResponse: OrderPositionResponse) {
        orderPositions.add(orderPositionResponse)
    }

    fun findAllByOrderIds(orderIds: List<String>): List<OrderPositionResponse> {
       return orderPositions.filter { orderIds.contains(it.orderId) }
    }

}
