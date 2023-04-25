package com.demoprojekt.webshop.controller

import com.demoprojekt.webshop.model.*
import com.demoprojekt.webshop.service.OrderService
import org.springframework.web.bind.annotation.*


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

    @PutMapping("/orders/{id}")
    fun updateOrder(
            @PathVariable id: String,
            @RequestBody request: OrderUpdateRequest
    ){
        orderService.updateOrder(id, request)
    }

    @GetMapping("/orders/{id}")
    fun getOrder(@PathVariable id: String): GetOrderResponse {
        return orderService.getOrder(id);
    }

}