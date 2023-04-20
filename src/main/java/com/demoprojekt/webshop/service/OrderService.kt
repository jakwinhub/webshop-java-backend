package com.demoprojekt.webshop.service

import com.demoprojekt.webshop.exceptions.IdNotFoundException
import com.demoprojekt.webshop.exceptions.WebshopException
import com.demoprojekt.webshop.model.*
import com.demoprojekt.webshop.repository.CustomerRepositroy
import com.demoprojekt.webshop.repository.OrderPositionRepository
import com.demoprojekt.webshop.repository.OrderRepository
import com.demoprojekt.webshop.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.net.IDN
import java.time.LocalDateTime
import java.util.UUID

@Service
class OrderService(
        val productRepository: ProductRepository,
        val orderRepositroy: OrderRepository,
        val orderPositionRepositroy: OrderPositionRepository,
        val customerRepository: CustomerRepositroy
) {


    fun createOrder(request: OrderCreateRequest): OrderResponse {

        customerRepository.findById(request.customerId)

        val orderResponse = OrderResponse(
                id = UUID.randomUUID().toString(),
                customerId = request.customerId,
                orderTimer = LocalDateTime.now(),
                status = OrderStatus.NEW,
                orderPositions = emptyList()
        )

        return orderRepositroy.save(orderResponse)
    }

    fun createNewPositionForOrder(orderId: String, request: OrderPositionCreateRequest): OrderPositionResponse {

        orderRepositroy.findById(orderId)
                ?: throw IdNotFoundException(message = "Order with $orderId not found",
                        statusCode = HttpStatus.BAD_REQUEST)

        if (productRepository.findById(request.productId).isEmpty)
            throw throw IdNotFoundException(message = "Product with ${request.productId} not found",
                    statusCode = HttpStatus.BAD_REQUEST)

        val orderPositionResponse = OrderPositionResponse(
                id = UUID.randomUUID().toString(),
                orderId = orderId,
                productId = request.productId,
                quantity = request.quantity
        )
        orderPositionRepositroy.save(orderPositionResponse)

        return orderPositionResponse
    }

    fun updateOrder(id: String, request: OrderUpdateRequest): OrderResponse {
        val order: OrderResponse = orderRepositroy.findById(id)
                ?: throw IdNotFoundException("Order with id" + id + "not found")

        val updatedOrder = order.copy(
                status = request.orderStatus ?: order.status
        )

        return orderRepositroy.save(updatedOrder)
    }
}