package com.demoprojekt.webshop.model

data class OrderPositionCreateRequest (
        val productId: String,
        val quantity: Long
) {

}
