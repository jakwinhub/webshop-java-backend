package com.demoprojekt.webshop.model

import java.time.LocalDateTime

data class GetOrderResponse (
        val id: String,
        val orderTime: LocalDateTime,
        val status: OrderStatus,
//        val customerId: String, //nur ID wird zurückgegeben
        val customer: CustomerResponse,  //alle Daten zum Kunden werden zurückgegeben
//        val orderPositionIds: List<String>,  // nur die IDs der Order Positons werden mitgegeben
        val orderPositions: List<GetOrderPositionResponse>
)

data class GetOrderPositionResponse(
        val id: String,
//        val productId: String, //nur die Product ID wird zurückgegeben
        val product: ProductResponse, //alle Daten zum Produkt werden mitgegeben
        val quantity: Long
)