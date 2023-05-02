package com.demoprojekt.webshop.service

import com.demoprojekt.webshop.entity.OrderEntity
import com.demoprojekt.webshop.exception.IdNotFoundException
import com.demoprojekt.webshop.model.*
import com.demoprojekt.webshop.repository.CustomerRepositroy
import com.demoprojekt.webshop.entity.OrderPositionEntity
import com.demoprojekt.webshop.repository.OrderRepository
import com.demoprojekt.webshop.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Suppress("DEPRECATION")
@Service
class OrderService(
        val productRepository: ProductRepository,
        val orderRepositroy: OrderRepository,
        val customerRepository: CustomerRepositroy
) {


    fun createOrder(request: OrderCreateRequest): OrderResponse {

        customerRepository.findById(request.customerId)

        val order = OrderEntity(
                id = UUID.randomUUID().toString(),
                customerId = request.customerId,
                orderTime = LocalDateTime.now(),
                status = OrderStatus.NEW,
                orderPositions = emptyList()
        )
        val savedOrder = orderRepositroy.save(order)

        return mapToResponse(savedOrder)
    }

    fun createNewPositionForOrder(orderId: String, request: OrderPositionCreateRequest): OrderPositionResponse {

        val order: OrderEntity = orderRepositroy.getOne(orderId)

        if (productRepository.findById(request.productId).isEmpty)
            throw throw IdNotFoundException(message = "Product with ${request.productId} not found",
                    statusCode = HttpStatus.BAD_REQUEST)

        val orderPosition = OrderPositionEntity(
                id = UUID.randomUUID().toString(),
                orderId = orderId,
                productId = request.productId,
                quantity = request.quantity
        )
        val updatedOrderPositions = order.orderPositions.plus(orderPosition)

        val updatedOrder = order.copy(
                orderPositions = updatedOrderPositions
        )

        orderRepositroy.save(updatedOrder)

        return mapToResponse(orderPosition)
    }

    companion object {
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
            orderTimer = savedOrder.orderTime,
            status = savedOrder.status,
            orderPositions = emptyList()
    )

    fun getOrder(id: String): GetOrderResponse {
        val order = orderRepositroy.getOne(id)

        val customer = customerRepository.getOne(order.customerId)

        val positions = order
                .orderPositions
                .map {
                    val productEntity = productRepository.getOne(it.productId)
                    GetOrderPositionResponse(
                            id = it.id,
                            quantity = it.quantity,
                            product = ProductResponse(
                                    productEntity.id,
                                    productEntity.name,
                                    productEntity.description,
                                    productEntity.priceInCent,
                                    productEntity.tags
                            )
                    )
                }

        return GetOrderResponse(
                id = order.id,
                status = order.status,
                orderTime = order.orderTime,
                customer = CustomerResponse(
                        id = customer.id,
                        firstName = customer.firstName,
                        lastName = customer.lastName,
                        email = customer.email
                ),
                orderPositions = positions
        )

    }
}