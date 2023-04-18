package com.demoprojekt.webshop.controller

import com.demoprojekt.webshop.model.OrderCreateRequest
import com.demoprojekt.webshop.model.OrderPositionCreateRequest
import com.demoprojekt.webshop.model.OrderResponse
import com.demoprojekt.webshop.service.OrderService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class OrderController(
        val orderService: OrderService
) {


    @PostMapping("/orders")
    fun createOrder(@RequestBody request: OrderCreateRequest): OrderResponse {
        return orderService.createOrder(request)
    }

    @PostMapping("/orders/{id}/positions")
    fun createOrderPosition(
            @PathVariable(name = "id") orderId: String,
            @RequestBody request: OrderPositionCreateRequest) {
        orderService.createNewPositionForOrder(orderId, request)
    }
}