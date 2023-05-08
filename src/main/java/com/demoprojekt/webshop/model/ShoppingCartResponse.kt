package com.demoprojekt.webshop.model

import io.swagger.annotations.ApiModelProperty

data class ShoppingCartResponse (
        val customerId: String,
        val orderPositions: List<OrderPositionResponse>,
        val totalAmountInCent: Long,
        @ApiModelProperty(value = "Delivery cost in cent")
        val deliveryCostInCent: Long,
        val deliveryOption: String
)
