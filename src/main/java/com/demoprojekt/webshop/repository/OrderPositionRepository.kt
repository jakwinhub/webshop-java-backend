package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.model.OrderPositionResponse

class OrderPositionRepository {

    val orderPositions = mutableListOf<OrderPositionResponse>()

    fun save(orderPositionResponse: OrderPositionResponse) {
        orderPositions.add(orderPositionResponse)
    }

}
