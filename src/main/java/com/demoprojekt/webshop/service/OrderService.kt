package com.demoprojekt.webshop.service

import com.demoprojekt.webshop.exceptions.IdNotFoundException
import com.demoprojekt.webshop.model.*
import com.demoprojekt.webshop.repository.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Suppress("DEPRECATION")
@Service
class OrderService(
        val productRepository: ProductRepository,
        val orderRepositroy: OrderRepository,
        val orderPositionRepositroy: OrderPositionRepository,
        val customerRepository: CustomerRepositroy
) {


    fun createOrder(request: OrderCreateRequest): OrderResponse {

        customerRepository.findById(request.customerId)

        val order = OrderEntity(
                id = UUID.randomUUID().toString(),
                customerId = request.customerId,
                orderTimer = LocalDateTime.now(),
                status = OrderStatus.NEW
        )
        val savedOrder = orderRepositroy.save(order)

        return mapToResponse(savedOrder)
    }

    fun createNewPositionForOrder(orderId: String, request: OrderPositionCreateRequest): OrderPositionResponse {

        orderRepositroy.findById(orderId)
                ?: throw IdNotFoundException(message = "Order with $orderId not found",
                        statusCode = HttpStatus.BAD_REQUEST)

        if (productRepository.findById(request.productId).isEmpty)
            throw throw IdNotFoundException(message = "Product with ${request.productId} not found",
                    statusCode = HttpStatus.BAD_REQUEST)

        val orderPosition = OrderPositionEntity(
                id = UUID.randomUUID().toString(),
                orderId = orderId,
                productId = request.productId,
                quantity = request.quantity
        )
        val savedOrderPosition = orderPositionRepositroy.save(orderPosition)

        return mapToResponse(savedOrderPosition)
    }

    companion object{
        fun mapToResponse(savedOrderPosition: OrderPositionEntity) =
                OrderPositionResponse(
                        id = savedOrderPosition.id,
                        orderId = savedOrderPosition.orderId,
                        productId = savedOrderPosition.productId,
                        quantity = savedOrderPosition.quantity
                )
    }

    fun updateOrder(id: String, request: OrderUpdateRequest): OrderResponse {
        val order = orderRepositroy.getOne(id)
        val updatedOrder = order.copy(
                status = request.orderStatus ?: order.status
        )

        val savedOrder = orderRepositroy.save(updatedOrder)

        return mapToResponse(savedOrder)
    }

    private fun mapToResponse(savedOrder: OrderEntity) = OrderResponse(
            id = savedOrder.id,
            customerId = savedOrder.customerId,
            orderTimer = savedOrder.orderTimer,
            status = savedOrder.status,
            orderPositions = emptyList()
    )
}